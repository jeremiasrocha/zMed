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

import br.com.techHouse.zmed.entity.Operadora;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.OperadoraNaoEncontradaException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.OperadoraTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class OperadoraDAO extends ZmedDataAbstract<Operadora> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Operadora recuperar(Serializable id) throws Exception {
		CriteriaQuery<Operadora> criteria = getCriteriaBuilder().createQuery(Operadora.class);
		Root<Operadora> root = criteria.from(Operadora.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Operadora recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Operadora> criteria = getCriteriaBuilder().createQuery(Operadora.class);
		Root<Operadora> root = criteria.from(Operadora.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Operadora recuperarPorNome(String nome) throws OperadoraNaoEncontradaException {
		try {
			CriteriaQuery<Operadora> criteria = getCriteriaBuilder().createQuery(Operadora.class);
			Root<Operadora> root = criteria.from(Operadora.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new OperadoraNaoEncontradaException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_OPERADORA_NAO_ENCONTRADA.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<Operadora> root, OperadoraTO operadoraTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(operadoraTO.getOperadora().getId())) {
			listaPredicate.add(comporFiltroPorId(operadoraTO.getOperadora().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(operadoraTO.getOperadora().getRegistroAns())) {
			listaPredicate.add(comporFiltroPorRegistroANS(operadoraTO.getOperadora().getRegistroAns().toString(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(operadoraTO.getOperadora().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(operadoraTO.getOperadora().getDataCadastro(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorRegistroANS(String registroANS, Root<Operadora> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("registroAns"))), "%"+registroANS.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<Operadora> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Operadora> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<Operadora> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<Operadora> pesquisar(OperadoraTO operadoraTO) {
		CriteriaQuery<Operadora> criteria = getCriteriaBuilder().createQuery(Operadora.class);
		Root<Operadora> root = criteria.from(Operadora.class);
		Predicate[] listaPredicate = comporFiltro(root, operadoraTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirOperadora(Operadora operadora) {
		CriteriaUpdate<Operadora> criteria = getCriteriaBuilder().createCriteriaUpdate(Operadora.class);
		Root<Operadora> root = criteria.from(Operadora.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", operadora.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),operadora.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}