package br.com.techHouse.zmed.enums;

public enum ZmedNavegacaoEnum {
	
	medicamento,
	medicamentoInventario,
	notaFiscal,
	fornecedor,
	operadora,
	parametrosOperadora,
	paciente,
	profissional,
	equipamento;
	
	public String getName(){
		return name();
	}

}
