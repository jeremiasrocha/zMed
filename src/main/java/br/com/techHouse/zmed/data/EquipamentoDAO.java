package br.com.techHouse.zmed.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.techHouse.zmed.entity.Equipamento;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.EquipamentoNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.EquipamentoTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class EquipamentoDAO extends ZmedDataAbstract<Equipamento> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Equipamento recuperar(Serializable id) throws Exception {
		CriteriaQuery<Equipamento> criteria = getCriteriaBuilder().createQuery(Equipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Equipamento recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Equipamento> criteria = getCriteriaBuilder().createQuery(Equipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Equipamento recuperarPorNome(String nome) throws EquipamentoNaoEncontradoException {
		try {
			CriteriaQuery<Equipamento> criteria = getCriteriaBuilder().createQuery(Equipamento.class);
			Root<Equipamento> root = criteria.from(Equipamento.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new EquipamentoNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_EQUIPAMENTO_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<Equipamento> root, EquipamentoTO equipamentoTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(equipamentoTO.getEquipamento().getApresentacao())) {
			listaPredicate.add(comporFiltroPorNome(equipamentoTO.getEquipamento().getApresentacao(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(equipamentoTO.getEquipamento().getReferencia())) {
			listaPredicate.add(comporFiltroPorReferencia(equipamentoTO.getEquipamento().getReferencia(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(equipamentoTO.getEquipamento().getId())) {
			listaPredicate.add(comporFiltroPorId(equipamentoTO.getEquipamento().getId(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String apresentacao, Root<Equipamento> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("apresentacao"))), "%"+apresentacao.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorReferencia(String referencia, Root<Equipamento> root) {
		return getCriteriaBuilder().equal(root.<String>get("referencia"), referencia);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Equipamento> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<Equipamento> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<Equipamento> pesquisar(EquipamentoTO equipamentoTO) {
		CriteriaQuery<Equipamento> criteria = getCriteriaBuilder().createQuery(Equipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		Predicate[] listaPredicate = comporFiltro(root, equipamentoTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Equipamento> pesquisarPorApresentacao(String apresentacao) {
		CriteriaQuery<Equipamento> criteria = getCriteriaBuilder().createQuery(Equipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("apresentacao"))), "%"+apresentacao.toLowerCase()+"%"))).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirEquipamento(Equipamento equipamento) {
		CriteriaUpdate<Equipamento> criteria = getCriteriaBuilder().createCriteriaUpdate(Equipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", equipamento.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),equipamento.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
	public Integer definirCodigoEquipamento() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT nextval('zmed_seq_cod_equipamento')");
	 	Query query = getManager().createNativeQuery(sb.toString());
		Object result = query.getSingleResult();
	 	return Integer.parseInt(result.toString());
	}

	/*public Boolean isEquipamentoCadastrada(String nome) throws Exception {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Equipamento> root = criteriaLong.from(Equipamento.class);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(
				getCriteriaBuilder().equal(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.get("nome"))), nome.toLowerCase()));
		return getManager().createQuery(criteriaLong).getSingleResult().intValue() > 0;
	}*/
	
	/*private Integer realizarCount(EquipamentoTO equipamentoTO) {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Equipamento> root = criteriaLong.from(Equipamento.class);
		Predicate[] listaPredicate = comporFiltro(root, equipamentoTO, criteriaLong);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(listaPredicate);
		return getManager().createQuery(criteriaLong).getSingleResult().intValue();
	}*/
	
}