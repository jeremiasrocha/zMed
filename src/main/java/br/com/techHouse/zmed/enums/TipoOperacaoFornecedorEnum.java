package br.com.techHouse.zmed.enums;

public enum TipoOperacaoFornecedorEnum {

	C("label.global.compra"),
	V("label.global.venda"),
	A("label.global.aluguel"),
	O("label.global.outros");
	
	private TipoOperacaoFornecedorEnum(String key){
		setKey(key);
	}
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
