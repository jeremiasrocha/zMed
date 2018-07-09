package br.com.techHouse.zmed.enums;

public enum FracionamentoMedicamentoEnum {

	A("label.global.ativo"),
	I("label.global.inativo"),
	E("label.global.excluido");
	
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
	
}
