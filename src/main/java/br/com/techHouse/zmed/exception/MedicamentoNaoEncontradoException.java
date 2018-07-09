package br.com.techHouse.zmed.exception;

public class MedicamentoNaoEncontradoException extends MedicamentoException {

	private static final long serialVersionUID = -8586045766479022529L;

	public MedicamentoNaoEncontradoException() {
		super();
	}

	public MedicamentoNaoEncontradoException(String msg) {
		super(msg);
	}

}