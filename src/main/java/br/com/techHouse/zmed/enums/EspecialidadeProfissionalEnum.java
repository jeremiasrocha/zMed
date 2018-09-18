package br.com.techHouse.zmed.enums;

public enum EspecialidadeProfissionalEnum {
		
	f("Fisioterapeuta"), 
	O("Fonoaudiólogo"),
	C("Médico clínico"),
	A("Médico pediatra"),
	E("Médico especialista"), 
	P("Psicologo"),
	S("Assistente Social"), 
	U("Terapeuta ocupacional"), 
	J("Enfermeiro"),
	T("Técnico de enfermagem - 12h"), 
	R("Técnico de enfermagem - 24h"),
	V("Técnico de enfermagem - 24h VM"), 
	X("Técnico de enfermagem - 6h"),
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
