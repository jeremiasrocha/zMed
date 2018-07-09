package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;
import br.com.techHouse.zmed.entity.Fornecedor;

public class FornecedorTO {

    private Fornecedor fornecedor;
    private List<Fornecedor> listaFornecedor;
    
	public Fornecedor getFornecedor() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Fornecedor> getListaFornecedor() {
		if (listaFornecedor == null) {
			listaFornecedor = new ArrayList<Fornecedor>();
		}
		return listaFornecedor;
	}
	
	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}
    
}