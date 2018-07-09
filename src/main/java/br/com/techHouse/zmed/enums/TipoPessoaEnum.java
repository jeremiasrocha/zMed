package br.com.techHouse.zmed.enums;

public enum TipoPessoaEnum {

	PF("Física"),
	PJ("Jurídica");

	private TipoPessoaEnum(String tipo) {
		this.tipo = tipo;
	}

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public String getName() {
		return name();
	}

}