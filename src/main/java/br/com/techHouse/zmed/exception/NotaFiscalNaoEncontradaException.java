package br.com.techHouse.zmed.exception;

public class NotaFiscalNaoEncontradaException extends NotaFiscalException {

	private static final long serialVersionUID = -42171713497866420L;

	public NotaFiscalNaoEncontradaException() {
		super();
	}

	public NotaFiscalNaoEncontradaException(String msg) {
		super(msg);
	}

}