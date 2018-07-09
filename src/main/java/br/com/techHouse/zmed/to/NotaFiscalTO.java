package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;
import br.com.techHouse.zmed.entity.NotaFiscal;

public class NotaFiscalTO {

    private NotaFiscal notaFiscal;
    private List<NotaFiscal> listaNotaFiscal;
    
	public NotaFiscal getNotaFiscal() {
		if (notaFiscal == null) {
			notaFiscal = new NotaFiscal();
		}
		return notaFiscal;
	}
	
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
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

}