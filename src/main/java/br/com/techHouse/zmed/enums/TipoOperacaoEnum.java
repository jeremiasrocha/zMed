package br.com.techHouse.zmed.enums;

public enum TipoOperacaoEnum {
	
	C("CADASTRAR"),
	A("ALTERAR"),
	E("EXCLUIR"),
	P("PESQUISAR");

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
