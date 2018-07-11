package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;
import br.com.techHouse.zmed.entity.ParametrosOperadora;

public class ParametrosOperadoraTO {

    private ParametrosOperadora parametrosOperadora;
    private List<ParametrosOperadora> listaParametrosOperadora;
    private Part fileContrato;
    private Part fileTabelaPrecos;
    
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

	public ParametrosOperadora getParametrosOperadora() {
		if (parametrosOperadora == null) {
			parametrosOperadora = new ParametrosOperadora();
		}
		return parametrosOperadora;
	}

	public void setParametrosOperadora(ParametrosOperadora parametrosOperadora) {
		this.parametrosOperadora = parametrosOperadora;
	}

	public List<ParametrosOperadora> getListaParametrosOperadora() {
		if (listaParametrosOperadora == null) {
			listaParametrosOperadora = new ArrayList<>();
		}
		return listaParametrosOperadora;
	}

	public void setListaParametrosOperadora(List<ParametrosOperadora> listaParametrosOperadora) {
		this.listaParametrosOperadora = listaParametrosOperadora;
	}
    
}