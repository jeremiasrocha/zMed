package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.com.techHouse.zmed.entity.Paciente;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.OperadoraService;
import br.com.techHouse.zmed.service.PacienteService;
import br.com.techHouse.zmed.to.PacienteTO;
import br.com.techHouse.zmed.util.UtilNullEmpty;

@ManagedBean(name = "pacienteController")
@ViewScoped
public class PacienteController extends ZmedController<PacienteTO> {

	private static final long serialVersionUID = 8029902120216625584L;
	private @Inject PacienteService pacienteService;
	private @Inject OperadoraService operadoraService;

	@PostConstruct
	private void inicializar() {
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setListaPaciente(pacienteService.pesquisar(getTo()));
			getTo().setListaOperadora(operadoraService.listar());
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.paciente.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirPaciente();
		pacienteService.incluir(getTo().getPaciente());
		adicionarMensagemIncluirSucesso();
		limparObjetoPaciente();
		pesquisar();
		return ZmedNavegacaoEnum.paciente.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarPaciente();
		pacienteService.alterar(getTo().getPaciente());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoPaciente();
		pesquisar();
		return ZmedNavegacaoEnum.paciente.getName();
	}

	public String excluir(Paciente paciente)throws Exception {
		definirDadosParaExcluirPaciente(paciente);
		pacienteService.excluirPaciente(paciente);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.paciente.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoPaciente();
		getTo().setListaPaciente(pacienteService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(Paciente paciente)throws Exception {
		recuperar(paciente.getId());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setPaciente(pacienteService.recuperar(id));
	}
	
	private void definirDadosParaIncluirPaciente() throws Exception {
		getTo().getPaciente().setDataCadastro(new Date());
		if(UtilNullEmpty.isNullOrEmpty(getTo().getPaciente().getTipoAtendimento())) {
			getTo().getPaciente().setOperadora(null);
			getTo().getPaciente().setNumeroCarteira(null);
		}
		//TODO ID USUARIO CADASTRO INSERIDO MANUALMENTE- PROVISÓRIO
		getTo().getPaciente().getUsuarioCadastro().setId(1);
	}
	
	private void definirDadosParaAlterarPaciente() throws Exception {
		getTo().getPaciente().setDataAlteracao(new Date());
	}
	
	private void definirDadosParaExcluirPaciente(Paciente paciente) throws Exception {
		paciente.setSituacao(StatusEnum.E.getKey());
	}

	private void limparObjetoPaciente() throws Exception {
		getTo().setPaciente(null);
	}

}