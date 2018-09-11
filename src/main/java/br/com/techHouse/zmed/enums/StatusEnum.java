package br.com.techHouse.zmed.enums;

public enum StatusEnum {

	A("Ativo"),
	I("Inativo"),
	E("Excluído");
	
	private StatusEnum(String key){
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
