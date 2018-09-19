package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Part;

import br.com.techHouse.zmed.entity.Equipamento;

public class EquipamentoTO {

    private Equipamento equipamento;
    private String[] tipoEquipamento;
    private String tipoLayoutArquivo;
    private Part file;
    private List<Equipamento> equipamentos;

	public Equipamento getEquipamento() {
		if (equipamento == null) {
			equipamento = new Equipamento();
		}
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getEquipamentos() {
		if (equipamentos == null) {
			equipamentos =  new ArrayList<>();
		}
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public String[] getTipoEquipamento() {
		return tipoEquipamento;
	}
	
	public String getTipoEquipamentoString() {
		String tipoRetorno = Arrays.toString(tipoEquipamento).replace("[", "");
		tipoRetorno = tipoRetorno.replace("]", "");
		return tipoRetorno;
	}

	public void setTipoEquipamento(String[] tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public String getTipoLayoutArquivo() {
		if (tipoLayoutArquivo == null) {
			tipoLayoutArquivo = new String();
		}
		return tipoLayoutArquivo;
	}

	public void setTipoLayoutArquivo(String tipoLayoutArquivo) {
		this.tipoLayoutArquivo = tipoLayoutArquivo;
	}
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}