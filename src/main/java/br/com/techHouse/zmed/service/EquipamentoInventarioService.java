package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.EquipamentoInventarioDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.EquipamentoInventario;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.EquipamentoInventarioTO;

@Stateless
public class EquipamentoInventarioService extends ServiceAbstract<EquipamentoInventario> {

	private @Inject EquipamentoInventarioDAO equipamentoInventarioDAO;

	@Override
	protected ZmedDataAbstract<EquipamentoInventario> getEntityBean() {
		return equipamentoInventarioDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EquipamentoInventario recuperarCompletoPorNome(String nome) throws Exception {
		EquipamentoInventario equipamentoInventario = equipamentoInventarioDAO.recuperarPorNome(nome);
		return equipamentoInventario;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EquipamentoInventario recuperarCompletoPorId(Integer id) throws Exception {
		EquipamentoInventario equipamentoInventario = equipamentoInventarioDAO.recuperarCompleto(id);
		return equipamentoInventario;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<EquipamentoInventarioTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EquipamentoInventario> pesquisar(EquipamentoInventarioTO equipamentoInventarioTO) throws Exception{
		return equipamentoInventarioDAO.pesquisar(equipamentoInventarioTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirEquipamentoInventario(EquipamentoInventario equipamentoInventario) throws Exception {
		equipamentoInventarioDAO.excluirEquipamentoInventario(equipamentoInventario);
	}
	
}