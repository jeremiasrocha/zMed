package br.com.techHouse.zmed.enums;

public enum FracionamentoMedicamentoEnum {

	Am("Ampola"),
	bl("Blister"),
	Ev("Envelope"),
	Fl("Flaconete"),
	Fr("Frasco"),
	Gt("Gotas"),
	pl("Pílula"),
	Sa("Sachê"),
	Sg("Seringa");
	
	private FracionamentoMedicamentoEnum(String key){
		setKey(key);
	}
		
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getName(){
		return name();
	}
	
}
