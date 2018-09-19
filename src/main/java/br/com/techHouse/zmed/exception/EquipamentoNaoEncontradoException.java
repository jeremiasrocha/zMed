package br.com.techHouse.zmed.exception;

public class EquipamentoNaoEncontradoException extends EquipamentoException {

	private static final long serialVersionUID = -5282742604452763322L;

	public EquipamentoNaoEncontradoException() {
		super();
	}

	public EquipamentoNaoEncontradoException(String msg) {
		super(msg);
	}

}