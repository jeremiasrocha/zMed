package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;
import br.com.techHouse.zmed.entity.Operadora;

public class OperadoraTO {

    private Operadora operadora;
    private List<Operadora> listaOperadora;
    
	public Operadora getOperadora() {
		if (operadora == null) {
			operadora = new Operadora();
		}
		return operadora;
	}
	
	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}
	
	public List<Operadora> getListaOperadora() {
		if (listaOperadora == null) {
			listaOperadora = new ArrayList<Operadora>();
		}
		return listaOperadora;
	}
	
	public void setListaOperadora(List<Operadora> listaOperadora) {
		this.listaOperadora = listaOperadora;
	}
    
}