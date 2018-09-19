package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.techHouse.zmed.entity.Profissional;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.ProfissionalService;
import br.com.techHouse.zmed.to.ProfissionalTO;
import br.com.techHouse.zmed.util.UtilArquivo;

@ManagedBean(name = "profissionalController")
@ViewScoped
public class ProfissionalController extends ZmedController<ProfissionalTO> {

	private static final long serialVersionUID = -2077678131500596753L;
	
	private @Inject ProfissionalService profissionalService;

	@PostConstruct
	private void inicializar() {
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setListaProfissional(profissionalService.pesquisar(getTo()));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.profissional.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirProfissional();
		profissionalService.incluir(getTo().getProfissional());
		adicionarMensagemIncluirSucesso();
		limparObjetoProfissional();
		pesquisar();
		return ZmedNavegacaoEnum.profissional.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarProfissional();
		profissionalService.alterar(getTo().getProfissional());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoProfissional();
		pesquisar();
		return ZmedNavegacaoEnum.profissional.getName();
	}

	public String excluir(Profissional profissional)throws Exception {
		definirDadosParaExcluirProfissional(profissional);
		profissionalService.excluirProfissional(profissional);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.profissional.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoProfissional();
		getTo().setListaProfissional(profissionalService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(Profissional profissional)throws Exception {
		recuperar(profissional.getId());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setProfissional(profissionalService.recuperar(id));
	}
	
	private void definirDadosParaIncluirProfissional() throws Exception {
		getTo().getProfissional().setDataCadastro(new Date());
		getTo().getProfissional().setStatus(StatusEnum.A.getKey());
		
		getTo().getProfissional().setAssinatura(UtilArquivo.converteInputStreamEmBytes(getTo().getImgAssinatura().getInputStream()));
		
		//PASSAGEM ID USUARIO CADASTRO MANUAL- PROVISÓRIO
		//getTo().getNotaFiscal().getUsuarioCadastro().setId(1);
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	public void downloadImagemAssinatura(Profissional profissional) throws Exception {
		download(UtilArquivo.converterBytesEmByteArrayOutputStream(profissional.getAssinatura()), "Assinatura " + profissional.getNome()+".jpg");
	}
	
	private void definirDadosParaAlterarProfissional() throws Exception {
		getTo().getProfissional().setDataAlteracao(new Date());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaExcluirProfissional(Profissional profissional) throws Exception {
		profissional.setStatus(StatusEnum.E.getKey());
	}

	private void limparObjetoProfissional() throws Exception {
		getTo().setProfissional(null);
	}

}