package br.com.techHouse.zmed.to;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import br.com.techHouse.zmed.entity.Profissional;

public class ProfissionalTO {

    private Profissional profissional;
    private List<Profissional> listaProfissional;
    private Part imagemAssinatura;
    private String cpf;
    private String cnpj;
    
	public String getCpf() {
		if (cpf == null) {
			cpf = new String();
		}
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		if (cnpj == null) {
			cnpj = new String();
		}
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Profissional getProfissional() {
		if (profissional == null) {
			profissional = new Profissional();
		}
		return profissional;
	}
	
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public List<Profissional> getListaProfissional() {
		if (listaProfissional == null) {
			listaProfissional = new ArrayList<Profissional>();
		}
		return listaProfissional;
	}
	
	public void setListaProfissional(List<Profissional> listaProfissional) {
		this.listaProfissional = listaProfissional;
	}
	
	public Part getImgAssinatura() {
		return imagemAssinatura;
	}

	public void setImgAssinatura(Part imagemAssinatura) {
		this.imagemAssinatura = imagemAssinatura;
	}
    
}