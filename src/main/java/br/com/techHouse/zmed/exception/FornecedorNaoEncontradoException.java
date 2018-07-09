package br.com.techHouse.zmed.exception;

public class FornecedorNaoEncontradoException extends MedicamentoException {

	private static final long serialVersionUID = -8586045766479022529L;

	public FornecedorNaoEncontradoException() {
		super();
	}

	public FornecedorNaoEncontradoException(String msg) {
		super(msg);
	}

}