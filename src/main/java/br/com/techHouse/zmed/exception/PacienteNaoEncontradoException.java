package br.com.techHouse.zmed.exception;

public class PacienteNaoEncontradoException extends PacienteException {

	private static final long serialVersionUID = -4853133312302007985L;

	public PacienteNaoEncontradoException() {
		super();
	}

	public PacienteNaoEncontradoException(String msg) {
		super(msg);
	}

}