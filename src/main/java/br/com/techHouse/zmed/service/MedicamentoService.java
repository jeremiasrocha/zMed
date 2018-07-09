package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.MedicamentoDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.Medicamento;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.MedicamentoTO;

@Stateless
public class MedicamentoService extends ServiceAbstract<Medicamento> {

	private @Inject MedicamentoDAO medicamentoDAO;

	@Override
	protected ZmedDataAbstract<Medicamento> getEntityBean() {
		return medicamentoDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medicamento recuperarCompletoPorNome(String nome) throws Exception {
		Medicamento medicamento = medicamentoDAO.recuperarPorNome(nome);
		return medicamento;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medicamento recuperarCompletoPorId(Integer id) throws Exception {
		Medicamento medicamento = medicamentoDAO.recuperarCompleto(id);
		return medicamento;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<MedicamentoTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Medicamento> pesquisar(MedicamentoTO medicamentoTO) throws Exception {
		return medicamentoDAO.pesquisar(medicamentoTO);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Medicamento> pesquisarPorApresentacao(String apresentacao) throws Exception{
		return medicamentoDAO.pesquisarPorApresentacao(apresentacao);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirMedicamento(Medicamento medicamento) throws Exception {
		medicamentoDAO.excluirMedicamento(medicamento);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Integer definirCodigoMedicamento() throws Exception{
		return medicamentoDAO.definirCodigoMedicamento();
	}
	
}