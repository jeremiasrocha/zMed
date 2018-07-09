package br.com.techHouse.zmed.exception;

public class MedicamentoInventarioNaoEncontradoException extends MedicamentoException {

	private static final long serialVersionUID = -8586045766479022529L;

	public MedicamentoInventarioNaoEncontradoException() {
		super();
	}

	public MedicamentoInventarioNaoEncontradoException(String msg) {
		super(msg);
	}

}