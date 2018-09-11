package br.com.techHouse.zmed.enums;

public enum TipoOperacaoEnum {
	
	C("Cadastrar"),
	A("Alterar"),
	E("Excluir"),
	P("Pesquisar");

	private TipoOperacaoEnum(String tipo) {
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
