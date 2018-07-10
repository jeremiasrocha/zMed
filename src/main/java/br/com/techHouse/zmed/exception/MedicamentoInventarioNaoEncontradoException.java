package br.com.techHouse.zmed.exception;

public class MedicamentoInventarioNaoEncontradoException extends MedicamentoInventarioException {

	private static final long serialVersionUID = -82109071495966679L;

	public MedicamentoInventarioNaoEncontradoException() {
		super();
	}

	public MedicamentoInventarioNaoEncontradoException(String msg) {
		super(msg);
	}

}