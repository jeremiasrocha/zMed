package br.com.techHouse.zmed.data;

import java.io.Serializable;
import java.sql.Timestamp;
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

import br.com.techHouse.zmed.entity.Medicamento;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.MedicamentoNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.MedicamentoTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class MedicamentoDAO extends ZmedDataAbstract<Medicamento> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Medicamento recuperar(Serializable id) throws Exception {
		CriteriaQuery<Medicamento> criteria = getCriteriaBuilder().createQuery(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Medicamento recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Medicamento> criteria = getCriteriaBuilder().createQuery(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Medicamento recuperarPorNome(String nome) throws MedicamentoNaoEncontradoException {
		try {
			CriteriaQuery<Medicamento> criteria = getCriteriaBuilder().createQuery(Medicamento.class);
			Root<Medicamento> root = criteria.from(Medicamento.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new MedicamentoNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_MEDICAMENTO_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<Medicamento> root, MedicamentoTO medicamentoTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(medicamentoTO.getMedicamento().getApresentacao())) {
			listaPredicate.add(comporFiltroPorNome(medicamentoTO.getMedicamento().getApresentacao(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(medicamentoTO.getMedicamento().getRegistroMS())) {
			listaPredicate.add(comporFiltroPorRegistroMS(medicamentoTO.getMedicamento().getRegistroMS(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(medicamentoTO.getMedicamento().getValidade())) {
			listaPredicate.add(comporFiltroPorDataValidade(medicamentoTO.getMedicamento().getValidade(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(medicamentoTO.getMedicamento().getId())) {
			listaPredicate.add(comporFiltroPorId(medicamentoTO.getMedicamento().getId(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String apresentacao, Root<Medicamento> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("apresentacao"))), "%"+apresentacao.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorRegistroMS(String registroMS, Root<Medicamento> root) {
		return getCriteriaBuilder().equal(root.<String>get("registroMS"), registroMS);
	}
	
	private Predicate comporFiltroPorDataValidade(Date dataValidade, Root<Medicamento> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("validade"), dataValidade);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Medicamento> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<Medicamento> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<Medicamento> pesquisar(MedicamentoTO medicamentoTO) {
		CriteriaQuery<Medicamento> criteria = getCriteriaBuilder().createQuery(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		Predicate[] listaPredicate = comporFiltro(root, medicamentoTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Medicamento> pesquisarPorApresentacao(String apresentacao) {
		CriteriaQuery<Medicamento> criteria = getCriteriaBuilder().createQuery(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("apresentacao"))), "%"+apresentacao.toLowerCase()+"%"))).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirMedicamento(Medicamento medicamento) {
		CriteriaUpdate<Medicamento> criteria = getCriteriaBuilder().createCriteriaUpdate(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", medicamento.getStatus());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),medicamento.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
	public Integer definirCodigoMedicamento() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT nextval('zmed_seq_cod_medicamento')");
	 	Query query = getManager().createNativeQuery(sb.toString());
		Object result = query.getSingleResult();
	 	return Integer.parseInt(result.toString());
	}

	/*public Boolean isMedicamentoCadastrada(String nome) throws Exception {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Medicamento> root = criteriaLong.from(Medicamento.class);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(
				getCriteriaBuilder().equal(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.get("nome"))), nome.toLowerCase()));
		return getManager().createQuery(criteriaLong).getSingleResult().intValue() > 0;
	}*/
	
	/*private Integer realizarCount(MedicamentoTO medicamentoTO) {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Medicamento> root = criteriaLong.from(Medicamento.class);
		Predicate[] listaPredicate = comporFiltro(root, medicamentoTO, criteriaLong);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(listaPredicate);
		return getManager().createQuery(criteriaLong).getSingleResult().intValue();
	}*/
	
}