package br.com.techHouse.zmed.enums;

public enum PerfilUsuarioEnum {

	A("label.perfil.usuario.administrador"),
	P("label.perfil.usuario.prestador"),
	S("label.perfil.usuario.profissional"),
	T("label.perfil.usuario.auditor");
	
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
