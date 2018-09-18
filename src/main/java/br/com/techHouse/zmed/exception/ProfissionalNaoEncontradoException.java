package br.com.techHouse.zmed.exception;

public class ProfissionalNaoEncontradoException extends ProfissionalException {

	private static final long serialVersionUID = 8490820564672055896L;

	public ProfissionalNaoEncontradoException() {
		super();
	}

	public ProfissionalNaoEncontradoException(String msg) {
		super(msg);
	}

}