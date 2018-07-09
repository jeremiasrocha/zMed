package br.com.techHouse.zmed.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.techHouse.zmed.entity.Fornecedor;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.FornecedorNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.FornecedorTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class FornecedorDAO extends ZmedDataAbstract<Fornecedor> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Fornecedor recuperar(Serializable id) throws Exception {
		CriteriaQuery<Fornecedor> criteria = getCriteriaBuilder().createQuery(Fornecedor.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Fornecedor recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Fornecedor> criteria = getCriteriaBuilder().createQuery(Fornecedor.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Fornecedor recuperarPorNome(String nome) throws FornecedorNaoEncontradoException {
		try {
			CriteriaQuery<Fornecedor> criteria = getCriteriaBuilder().createQuery(Fornecedor.class);
			Root<Fornecedor> root = criteria.from(Fornecedor.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new FornecedorNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_FORNECEDOR_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<Fornecedor> root, FornecedorTO fornecedorTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(fornecedorTO.getFornecedor().getId())) {
			listaPredicate.add(comporFiltroPorId(fornecedorTO.getFornecedor().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(fornecedorTO.getFornecedor().getCodigo())) {
			listaPredicate.add(comporFiltroPorCodigo(fornecedorTO.getFornecedor().getCodigo().toString(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(fornecedorTO.getFornecedor().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(fornecedorTO.getFornecedor().getDataCadastro(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorCodigo(String codigo, Root<Fornecedor> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("codigo"))), "%"+codigo.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<Fornecedor> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Fornecedor> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<Fornecedor> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<Fornecedor> pesquisar(FornecedorTO fornecedorTO) {
		CriteriaQuery<Fornecedor> criteria = getCriteriaBuilder().createQuery(Fornecedor.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);
		Predicate[] listaPredicate = comporFiltro(root, fornecedorTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirFornecedor(Fornecedor fornecedor) {
		CriteriaUpdate<Fornecedor> criteria = getCriteriaBuilder().createCriteriaUpdate(Fornecedor.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", fornecedor.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),fornecedor.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}