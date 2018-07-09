package br.com.techHouse.zmed.enums;

public enum UfEnum {

	A("label.perfil.usuario.administrador"),
	P("label.perfil.usuario.prestador"),
	S("label.perfil.usuario.profissional"),
	T("label.perfil.usuario.auditor");
	
	private UfEnum(String key){
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
