package br.com.techHouse.zmed.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.techHouse.zmed.data.FornecedorDAO;
import br.com.techHouse.zmed.data.ZmedDataAbstract;
import br.com.techHouse.zmed.entity.Fornecedor;
import br.com.techHouse.zmed.to.FiltroTO;
import br.com.techHouse.zmed.to.FornecedorTO;

@Stateless
public class PacienteService extends ServiceAbstract<Fornecedor> {

	private @Inject FornecedorDAO fornecedorDAO;

	@Override
	protected ZmedDataAbstract<Fornecedor> getEntityBean() {
		return fornecedorDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Fornecedor recuperarCompletoPorNome(String nome) throws Exception {
		Fornecedor fornecedor = fornecedorDAO.recuperarPorNome(nome);
		return fornecedor;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Fornecedor recuperarCompletoPorId(Integer id) throws Exception {
		Fornecedor fornecedor = fornecedorDAO.recuperarCompleto(id);
		return fornecedor;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<FornecedorTO> filtro) throws Exception {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Fornecedor> pesquisar(FornecedorTO fornecedorTO) throws Exception{
		return fornecedorDAO.pesquisar(fornecedorTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirFornecedor(Fornecedor fornecedor) throws Exception {
		fornecedorDAO.excluirFornecedor(fornecedor);
	}
	
}