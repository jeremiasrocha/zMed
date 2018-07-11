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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.techHouse.zmed.entity.ParametrosOperadora;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.ParametrosOperadoraNaoEncontradosException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.ParametrosOperadoraTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class ParametrosOperadoraDAO extends ZmedDataAbstract<ParametrosOperadora> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public ParametrosOperadora recuperar(Serializable id) throws Exception {
		CriteriaQuery<ParametrosOperadora> criteria = getCriteriaBuilder().createQuery(ParametrosOperadora.class);
		Root<ParametrosOperadora> root = criteria.from(ParametrosOperadora.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public ParametrosOperadora recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<ParametrosOperadora> criteria = getCriteriaBuilder().createQuery(ParametrosOperadora.class);
		Root<ParametrosOperadora> root = criteria.from(ParametrosOperadora.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public ParametrosOperadora recuperarPorNome(String nome) throws ParametrosOperadoraNaoEncontradosException {
		try {
			CriteriaQuery<ParametrosOperadora> criteria = getCriteriaBuilder().createQuery(ParametrosOperadora.class);
			Root<ParametrosOperadora> root = criteria.from(ParametrosOperadora.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new ParametrosOperadoraNaoEncontradosException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_PARAMETROS_OPERADORA_NAO_ENCONTRADOS.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<ParametrosOperadora> root, ParametrosOperadoraTO parametrosOperadoraTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(parametrosOperadoraTO.getParametrosOperadora().getId())) {
			listaPredicate.add(comporFiltroPorId(parametrosOperadoraTO.getParametrosOperadora().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(parametrosOperadoraTO.getParametrosOperadora().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(parametrosOperadoraTO.getParametrosOperadora().getDataCadastro(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<ParametrosOperadora> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<ParametrosOperadora> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<ParametrosOperadora> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<ParametrosOperadora> pesquisar(ParametrosOperadoraTO parametrosOperadoraTO) {
		CriteriaQuery<ParametrosOperadora> criteria = getCriteriaBuilder().createQuery(ParametrosOperadora.class);
		Root<ParametrosOperadora> root = criteria.from(ParametrosOperadora.class);
		Predicate[] listaPredicate = comporFiltro(root, parametrosOperadoraTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
}