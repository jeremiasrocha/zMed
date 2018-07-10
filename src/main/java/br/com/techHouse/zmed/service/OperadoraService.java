package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.OperadoraDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.Operadora;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.OperadoraTO;

@Stateless
public class OperadoraService extends ServiceAbstract<Operadora> {

	private @Inject OperadoraDAO operadoraDAO;

	@Override
	protected ZmedDataAbstract<Operadora> getEntityBean() {
		return operadoraDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Operadora recuperarCompletoPorNome(String nome) throws Exception {
		Operadora operadora = operadoraDAO.recuperarPorNome(nome);
		return operadora;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Operadora recuperarCompletoPorId(Integer id) throws Exception {
		Operadora operadora = operadoraDAO.recuperarCompleto(id);
		return operadora;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<OperadoraTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Operadora> pesquisar(OperadoraTO operadoraTO) throws Exception{
		return operadoraDAO.pesquisar(operadoraTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirOperadora(Operadora operadora) throws Exception {
		operadoraDAO.excluirOperadora(operadora);
	}
	
}