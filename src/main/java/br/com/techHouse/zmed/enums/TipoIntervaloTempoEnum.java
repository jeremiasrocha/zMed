package br.com.techHouse.zmed.enums;

public enum TipoIntervaloTempoEnum {

	HORA("Hora"),
	DIA("Dia"),
	MES("Mês"),
	ANO("Ano");

	private String key;

	private TipoIntervaloTempoEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name();
	}

}