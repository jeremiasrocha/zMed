package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.EquipamentoDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.Equipamento;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.EquipamentoTO;

@Stateless
public class EquipamentoService extends ServiceAbstract<Equipamento> {

	private @Inject EquipamentoDAO equipamentoDAO;

	@Override
	protected ZmedDataAbstract<Equipamento> getEntityBean() {
		return equipamentoDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Equipamento recuperarCompletoPorNome(String nome) throws Exception {
		Equipamento equipamento = equipamentoDAO.recuperarPorNome(nome);
		return equipamento;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Equipamento recuperarCompletoPorId(Integer id) throws Exception {
		Equipamento equipamento = equipamentoDAO.recuperarCompleto(id);
		return equipamento;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<EquipamentoTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Equipamento> pesquisar(EquipamentoTO equipamentoTO) throws Exception {
		return equipamentoDAO.pesquisar(equipamentoTO);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Equipamento> pesquisarPorApresentacao(String apresentacao) throws Exception{
		return equipamentoDAO.pesquisarPorApresentacao(apresentacao);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirEquipamento(Equipamento equipamento) throws Exception {
		equipamentoDAO.excluirEquipamento(equipamento);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Integer definirCodigoEquipamento() throws Exception{
		return equipamentoDAO.definirCodigoEquipamento();
	}
	
}