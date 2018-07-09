package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.MedicamentoInventarioDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.MedicamentoInventario;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.MedicamentoInventarioTO;

@Stateless
public class MedicamentoInventarioService extends ServiceAbstract<MedicamentoInventario> {

	private @Inject MedicamentoInventarioDAO medicamentoInventarioDAO;

	@Override
	protected ZmedDataAbstract<MedicamentoInventario> getEntityBean() {
		return medicamentoInventarioDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MedicamentoInventario recuperarCompletoPorNome(String nome) throws Exception {
		MedicamentoInventario medicamentoInventario = medicamentoInventarioDAO.recuperarPorNome(nome);
		return medicamentoInventario;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MedicamentoInventario recuperarCompletoPorId(Integer id) throws Exception {
		MedicamentoInventario medicamentoInventario = medicamentoInventarioDAO.recuperarCompleto(id);
		return medicamentoInventario;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<MedicamentoInventarioTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MedicamentoInventario> pesquisar(MedicamentoInventarioTO medicamentoInventarioTO) throws Exception{
		return medicamentoInventarioDAO.pesquisar(medicamentoInventarioTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirMedicamentoInventario(MedicamentoInventario medicamentoInventario) throws Exception {
		medicamentoInventarioDAO.excluirMedicamentoInventario(medicamentoInventario);
	}
	
}