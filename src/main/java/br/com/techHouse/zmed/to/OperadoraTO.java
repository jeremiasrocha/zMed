package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import br.com.techHouse.zmed.entity.Operadora;

public class OperadoraTO {

    private Operadora operadora;
    private List<Operadora> listaOperadora;
    private Part fileContrato;
    private Part fileTabelaPrecos;
    
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
	
	public Part getFileContrato() {
		return fileContrato;
	}

	public void setFileContrato(Part fileContrato) {
		this.fileContrato = fileContrato;
	}
	
	public Part getFileTabelaPrecos() {
		return fileTabelaPrecos;
	}

	public void setFileTabelaPrecos(Part fileTabelaPrecos) {
		this.fileTabelaPrecos = fileTabelaPrecos;
	}
    
}