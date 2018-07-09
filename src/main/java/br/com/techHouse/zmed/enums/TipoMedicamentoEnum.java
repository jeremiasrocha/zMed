package br.com.techHouse.zmed.enums;

public enum TipoMedicamentoEnum {
	
	G("Genérico"),
	NG("Não Genérico");

	private TipoMedicamentoEnum(String tipo) {
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
