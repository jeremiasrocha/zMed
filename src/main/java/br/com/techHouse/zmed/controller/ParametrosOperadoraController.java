package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.techHouse.zmed.entity.ParametrosOperadora;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.OperadoraService;
import br.com.techHouse.zmed.service.ParametrosOperadoraService;
import br.com.techHouse.zmed.to.ParametrosOperadoraTO;

@ManagedBean(name = "parametrosOperadoraController")
@ViewScoped
public class ParametrosOperadoraController extends ZmedController<ParametrosOperadoraTO> {

	private static final long serialVersionUID = 2279068945912404629L;
	
	private @Inject ParametrosOperadoraService ParametrosOperadoraService;
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
			getTo().setListaParametrosOperadora(ParametrosOperadoraService.pesquisar(getTo()));
			getTo().setListaOperadora(operadoraService.listar());
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.parametrosOperadora.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirParametrosOperadora();
		ParametrosOperadoraService.incluir(getTo().getParametrosOperadora());
		adicionarMensagemIncluirSucesso();
		limparObjetoParametrosOperadora();
		pesquisar();
		return ZmedNavegacaoEnum.parametrosOperadora.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarParametrosOperadora();
		ParametrosOperadoraService.alterar(getTo().getParametrosOperadora());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoParametrosOperadora();
		pesquisar();
		return ZmedNavegacaoEnum.parametrosOperadora.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoParametrosOperadora();
		getTo().setListaParametrosOperadora(ParametrosOperadoraService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(ParametrosOperadora ParametrosOperadora)throws Exception {
		recuperar(ParametrosOperadora.getId());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setParametrosOperadora(ParametrosOperadoraService.recuperar(id));
	}
	
	private void definirDadosParaIncluirParametrosOperadora() throws Exception {
		getTo().getParametrosOperadora().setDataCadastro(new Date());
		getTo().getParametrosOperadora();
		//PASSAGEM ID USUARIO CADASTRO MANUAL- PROVISÓRIO
		//getTo().getNotaFiscal().getUsuarioCadastro().setId(1);
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaAlterarParametrosOperadora() throws Exception {
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}

	private void limparObjetoParametrosOperadora() throws Exception {
		getTo().setParametrosOperadora(null);
	}

}