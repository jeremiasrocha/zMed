package br.com.techHouse.zmed.enums;

public enum SituacaoPacienteEnum {
	
	V("Vinculado"),
	NV("Não Vinculado");

	private SituacaoPacienteEnum(String tipo) {
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