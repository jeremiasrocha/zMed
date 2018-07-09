package br.com.techHouse.zmed.enums;

public enum TipoNotaFiscalEnum {

	E("Entrada"),
	S("Saída");

	private TipoNotaFiscalEnum(String tipo) {
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