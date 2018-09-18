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

import br.com.techHouse.zmed.entity.Paciente;
import br.com.techHouse.zmed.enums.ZmedMensagemEnum;
import br.com.techHouse.zmed.exception.PacienteNaoEncontradoException;
import br.com.techHouse.zmed.message.MensagemFactory;
import br.com.techHouse.zmed.to.PacienteTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@Stateless
public class PacienteDAO extends ZmedDataAbstract<Paciente> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Paciente recuperar(Serializable id) throws Exception {
		CriteriaQuery<Paciente> criteria = getCriteriaBuilder().createQuery(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Paciente recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Paciente> criteria = getCriteriaBuilder().createQuery(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Paciente recuperarPorNome(String nome) throws PacienteNaoEncontradoException {
		try {
			CriteriaQuery<Paciente> criteria = getCriteriaBuilder().createQuery(Paciente.class);
			Root<Paciente> root = criteria.from(Paciente.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new PacienteNaoEncontradoException(mensagemFactory.getMensagem(ZmedMensagemEnum.UC_FORNECEDOR_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	private Predicate[] comporFiltro(Root<Paciente> root, PacienteTO pacienteTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		listaPredicate.add(comporFiltroPorDataExclusao(root));
		if (!UtilNullEmpty.isNullOrEmpty(pacienteTO.getPaciente().getId())) {
			listaPredicate.add(comporFiltroPorId(pacienteTO.getPaciente().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(pacienteTO.getPaciente().getCpf())) {
			listaPredicate.add(comporFiltroPorCPF(pacienteTO.getPaciente().getCpf().toString(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(pacienteTO.getPaciente().getDataCadastro())) {
			listaPredicate.add(comporFiltroPorDataCadastro(pacienteTO.getPaciente().getDataCadastro(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(pacienteTO.getPaciente().getNome())) {
			listaPredicate.add(comporFiltroPorNome(pacienteTO.getPaciente().getNome().toString(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String nome, Root<Paciente> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("nome"))), "%"+nome.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorCPF(String cpf, Root<Paciente> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("cpf"))), "%"+cpf.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorDataCadastro(Date dataCadastro, Root<Paciente> root) {
		return getCriteriaBuilder().equal(root.<Timestamp>get("dataCadastro"), dataCadastro);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Paciente> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorDataExclusao(Root<Paciente> root) {
		return getCriteriaBuilder().or(getCriteriaBuilder().isNull(root.<Date>get("dataExclusao")));
	}

	public List<Paciente> pesquisar(PacienteTO pacienteTO) {
		CriteriaQuery<Paciente> criteria = getCriteriaBuilder().createQuery(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		Predicate[] listaPredicate = comporFiltro(root, pacienteTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirPaciente(Paciente paciente) {
		CriteriaUpdate<Paciente> criteria = getCriteriaBuilder().createCriteriaUpdate(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		criteria.set("dataExclusao", new Date());
		criteria.set("status", paciente.getSituacao());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),paciente.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}
	
}