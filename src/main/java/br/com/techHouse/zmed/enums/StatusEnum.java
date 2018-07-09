package br.com.techHouse.zmed.enums;

public enum StatusEnum {

	A("label.global.ativo"),
	I("label.global.inativo"),
	E("label.global.excluido");
	
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
