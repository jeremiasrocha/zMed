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

import br.com.techHouse.zmed.entity.Profissional;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.ProfissionalNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.ProfissionalTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class ProfissionalDAO extends ZmedDataAbstract<Profissional> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Profissional recuperar(Serializable id) throws Exception {
		CriteriaQuery<Profissional> criteria = getCriteriaBuilder().createQuery(Profissional.class);
		Root<Profissional> root = criteria.from(Profissional.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Profissional recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Profissional> criteria = getCriteriaBuilder().createQuery(Profissional.class);
		Root<Profissional> root = criteria.from(Profissional.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Profissional recuperarPorNome(String nome) throws ProfissionalNaoEncontradoException {
		try {
			CriteriaQuery<Profissional> criteria = getCriteriaBuilder().createQuery(Profissional.class);
			Root<Profissional> root = criteria.from(Profissional.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new ProfissionalNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_PROFISSIONAL_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<Profissional> root, ProfissionalTO profissionalTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(profissionalTO.getProfissional().getId())) {
			listaPredicate.add(comporFiltroPorId(profissionalTO.getProfissional().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(profissionalTO.getProfissional().getCodigo())) {
			listaPredicate.add(comporFiltroPorCodigo(profissionalTO.getProfissional().getCodigo().toString(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(profissionalTO.getProfissional().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(profissionalTO.getProfissional().getDataCadastro(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(profissionalTO.getProfissional().getNome())) {
			listaPredicate.add(comporFiltroPorNome(profissionalTO.getProfissional().getNome().toString(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorCodigo(String codigo, Root<Profissional> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("codigo"))), "%"+codigo.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorNome(String nome, Root<Profissional> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("nome"))), "%"+nome.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<Profissional> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Profissional> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<Profissional> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<Profissional> pesquisar(ProfissionalTO profissionalTO) {
		CriteriaQuery<Profissional> criteria = getCriteriaBuilder().createQuery(Profissional.class);
		Root<Profissional> root = criteria.from(Profissional.class);
		Predicate[] listaPredicate = comporFiltro(root, profissionalTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirProfissional(Profissional profissional) {
		CriteriaUpdate<Profissional> criteria = getCriteriaBuilder().createCriteriaUpdate(Profissional.class);
		Root<Profissional> root = criteria.from(Profissional.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", profissional.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),profissional.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}