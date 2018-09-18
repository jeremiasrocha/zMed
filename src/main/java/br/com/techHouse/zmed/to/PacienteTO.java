package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;

import br.com.techHouse.zmed.entity.Operadora;
import br.com.techHouse.zmed.entity.Paciente;

public class PacienteTO {

    private Paciente paciente;
    private List<Paciente> listaPaciente;
    private List<Operadora> listaOperadora;
    
	public Paciente getPaciente() {
		if (paciente == null) {
			paciente = new Paciente();
		}
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public List<Paciente> getListaPaciente() {
		if (listaPaciente == null) {
			listaPaciente = new ArrayList<Paciente>();
		}
		return listaPaciente;
	}
	
	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
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