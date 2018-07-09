package br.com.techHouse.zmed.exception;

public class OperadoraNaoEncontradoException extends MedicamentoException {

	private static final long serialVersionUID = -8586045766479022529L;

	public OperadoraNaoEncontradoException() {
		super();
	}

	public OperadoraNaoEncontradoException(String msg) {
		super(msg);
	}

}