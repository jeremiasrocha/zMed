package br.com.techHouse.zmed.enums;

public enum EspecialidadeProfissionalEnum {
		
	f("Fisioterapeuta"), 
	O("Fonoaudiólogo"),
	C("Médico Clínico"),
	A("Médico Pediatra"),
	E("Médico Especialista"), 
	P("Psicologo"),
	S("Assistente Social"), 
	U("Terapeuta Ocupacional"), 
	J("Enfermeiro"),
	T("Técnico de Enfermagem - 12h"), 
	R("Técnico de Enfermagem - 24h"),
	V("Técnico de Enfermagem - 24h VM"), 
	X("Técnico de Enfermagem - 6h"),
	Z("Nutricionista");

	private EspecialidadeProfissionalEnum(String tipo) {
		this.tipo = tipo;
	}

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public String getName() {
		return name();
	}

}
