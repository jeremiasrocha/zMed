package br.com.techHouse.zmed.exception;

public class NotaFiscalNaoEncontradaException extends MedicamentoException {

	private static final long serialVersionUID = -8586045766479022529L;

	public NotaFiscalNaoEncontradaException() {
		super();
	}

	public NotaFiscalNaoEncontradaException(String msg) {
		super(msg);
	}

}