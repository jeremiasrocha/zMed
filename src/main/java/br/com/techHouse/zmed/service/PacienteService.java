package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.PacienteDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.Paciente;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.PacienteTO;

@Stateless
public class PacienteService extends ServiceAbstract<Paciente> {

	private @Inject PacienteDAO pacienteDAO;

	@Override
	protected ZmedDataAbstract<Paciente> getEntityBean() {
		return pacienteDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Paciente recuperarCompletoPorNome(String nome) throws Exception {
		Paciente paciente = pacienteDAO.recuperarPorNome(nome);
		return paciente;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Paciente recuperarCompletoPorId(Integer id) throws Exception {
		Paciente paciente = pacienteDAO.recuperarCompleto(id);
		return paciente;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<PacienteTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Paciente> pesquisar(PacienteTO pacienteTO) throws Exception{
		return pacienteDAO.pesquisar(pacienteTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirPaciente(Paciente paciente) throws Exception {
		pacienteDAO.excluirPaciente(paciente);
	}
	
}