package br.com.techHouse.zmed.enums;

public enum ZmedNavegacaoEnum {
	
	medicamento,
	medicamentoInventario,
	notaFiscal,
	fornecedor,
	operadora,
	parametrosOperadora,
	paciente;
	
	public String getName(){
		return name();
	}

}
