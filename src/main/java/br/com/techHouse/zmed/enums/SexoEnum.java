package br.com.techHouse.zmed.enums;

public enum SexoEnum {

	M("Masculino","1"),
	F("Feminino","2");

	private String key;
	private String codigo;

	private SexoEnum(String key, String codigo) {
		this.key = key;
		this.codigo = codigo;
	}

	public String getKey() {
		return key;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getName() {
		return name();
	}

	public static SexoEnum getEnum(String nome) {
		for (SexoEnum sexo : values()) {
			if (sexo.getName().equalsIgnoreCase(nome)) {
				return sexo;
			}
		}
		return null;
	}

}