package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;

import br.com.techHouse.zmed.entity.Medicamento;
import br.com.techHouse.zmed.entity.MedicamentoInventario;
import br.com.techHouse.zmed.entity.NotaFiscal;

public class MedicamentoInventarioTO {

    private MedicamentoInventario medicamentoInventario;
    private List<MedicamentoInventario> listaMedicamentoInventario;
    private List<Medicamento> listaMedicamento; 
    private List<NotaFiscal> listaNotaFiscal;

	public MedicamentoInventario getMedicamentoInventario() {
		if (medicamentoInventario == null) {
			medicamentoInventario = new MedicamentoInventario();
			medicamentoInventario.setMedicamento(new Medicamento());
			medicamentoInventario.setNotaFiscal(new NotaFiscal());
		}
		return medicamentoInventario;
	}

	public void setMedicamentoInventario(MedicamentoInventario medicamentoInventario) {
		this.medicamentoInventario = medicamentoInventario;
	}

	public List<MedicamentoInventario> getListaMedicamentoInventario() {
		if (listaMedicamentoInventario == null) {
			listaMedicamentoInventario = new ArrayList<MedicamentoInventario>();
		}
		return listaMedicamentoInventario;
	}

	public void setListaMedicamentoInventario(List<MedicamentoInventario> listaMedicamentoInventario) {
		this.listaMedicamentoInventario = listaMedicamentoInventario;
	}

	public List<NotaFiscal> getListaNotaFiscal() {
		if (listaNotaFiscal == null) {
			listaNotaFiscal = new ArrayList<NotaFiscal>();
		}
		return listaNotaFiscal;
	}

	public void setListaNotaFiscal(List<NotaFiscal> listaNotaFiscal) {
		this.listaNotaFiscal = listaNotaFiscal;
	}

	public List<Medicamento> getListaMedicamento() {
		if (listaMedicamento == null) {
			listaMedicamento = new ArrayList<Medicamento>();
		}
		return listaMedicamento;
	}

	public void setListaMedicamento(List<Medicamento> listaMedicamento) {
		this.listaMedicamento = listaMedicamento;
	}

}