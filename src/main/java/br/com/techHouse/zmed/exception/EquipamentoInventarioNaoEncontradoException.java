package br.com.techHouse.zmed.exception;

public class EquipamentoInventarioNaoEncontradoException extends EquipamentoInventarioException {

	private static final long serialVersionUID = 4582681402300976255L;

	public EquipamentoInventarioNaoEncontradoException() {
		super();
	}

	public EquipamentoInventarioNaoEncontradoException(String msg) {
		super(msg);
	}

}