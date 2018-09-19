package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;

import br.com.techHouse.zmed.entity.Equipamento;
import br.com.techHouse.zmed.entity.EquipamentoInventario;
import br.com.techHouse.zmed.entity.NotaFiscal;

public class EquipamentoInventarioTO {

    private EquipamentoInventario equipamentoInventario;
    private List<EquipamentoInventario> listaEquipamentoInventario;
    private List<Equipamento> listaEquipamento; 
    private List<NotaFiscal> listaNotaFiscal;

	public EquipamentoInventario getEquipamentoInventario() {
		if (equipamentoInventario == null) {
			equipamentoInventario = new EquipamentoInventario();
			equipamentoInventario.setEquipamento(new Equipamento());
			equipamentoInventario.setNotaFiscal(new NotaFiscal());
		}
		return equipamentoInventario;
	}

	public void setEquipamentoInventario(EquipamentoInventario equipamentoInventario) {
		this.equipamentoInventario = equipamentoInventario;
	}

	public List<EquipamentoInventario> getListaEquipamentoInventario() {
		if (listaEquipamentoInventario == null) {
			listaEquipamentoInventario = new ArrayList<EquipamentoInventario>();
		}
		return listaEquipamentoInventario;
	}

	public void setListaEquipamentoInventario(List<EquipamentoInventario> listaEquipamentoInventario) {
		this.listaEquipamentoInventario = listaEquipamentoInventario;
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

	public List<Equipamento> getListaEquipamento() {
		if (listaEquipamento == null) {
			listaEquipamento = new ArrayList<Equipamento>();
		}
		return listaEquipamento;
	}

	public void setListaEquipamento(List<Equipamento> listaEquipamento) {
		this.listaEquipamento = listaEquipamento;
	}

}