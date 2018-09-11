package br.com.techHouse.zmed.enums;

public enum PerfilUsuarioEnum {

	A("Administrador"),
	P("Prestador"),
	S("Profissional"),
	T("Auditor");
	
	private PerfilUsuarioEnum(String key){
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
