package br.com.techHouse.zmed.enums;

public enum TipoOperacaoFornecedorEnum {

	C("Compra"),
	V("Venda"),
	A("Aluguel"),
	O("Outros");
	
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
