package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Part;

import br.com.techHouse.zmed.entity.Medicamento;

public class MedicamentoTO {

    private Medicamento medicamento;
    private String[] tipoMedicamento;
    private String tipoLayoutArquivo;
    private Part file;
    private List<Medicamento> medicamentos;

	public Medicamento getMedicamento() {
		if (medicamento == null) {
			medicamento = new Medicamento();
		}
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getMedicamentos() {
		if (medicamentos == null) {
			medicamentos =  new ArrayList<>();
		}
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String[] getTipoMedicamento() {
		return tipoMedicamento;
	}
	
	public String getTipoMedicamentoString() {
		String tipoRetorno = Arrays.toString(tipoMedicamento).replace("[", "");
		tipoRetorno = tipoRetorno.replace("]", "");
		return tipoRetorno;
	}

	public void setTipoMedicamento(String[] tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
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