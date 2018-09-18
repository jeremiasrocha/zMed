package br.com.techHouse.zmed.enums;

public enum TipoAtendimentoProfissionalEnum {

	C("Consulta"),
	S("Sessão"),
	V("Visita");
	
	private TipoAtendimentoProfissionalEnum(String key){
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
