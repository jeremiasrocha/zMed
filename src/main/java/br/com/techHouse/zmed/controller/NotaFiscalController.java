package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.com.techHouse.zmed.entity.NotaFiscal;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.NotaFiscalService;
import br.com.techHouse.zmed.to.NotaFiscalTO;

@ManagedBean(name = "notaFiscalController")
@ViewScoped
public class NotaFiscalController extends ZmedController<NotaFiscalTO> {

	private static final long serialVersionUID = -4744804678678661350L;
	
	private @Inject NotaFiscalService notaFiscalService;

	@PostConstruct
	private void inicializar() {
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setListaNotaFiscal(notaFiscalService.pesquisar(getTo()));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.notaFiscal.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirNotaFiscal();
		notaFiscalService.incluir(getTo().getNotaFiscal());
		adicionarMensagemIncluirSucesso();
		limparObjetoNotaFiscal();
		pesquisar();
		return ZmedNavegacaoEnum.notaFiscal.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarNotaFiscal();
		notaFiscalService.alterar(getTo().getNotaFiscal());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoNotaFiscal();
		pesquisar();
		return ZmedNavegacaoEnum.notaFiscal.getName();
	}

	public String excluir(NotaFiscal notaFiscal)throws Exception {
		definirDadosParaExcluirNotaFiscal(notaFiscal);
		notaFiscalService.excluirNotaFiscal(notaFiscal);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.notaFiscal.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoNotaFiscal();
		getTo().setListaNotaFiscal(notaFiscalService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(NotaFiscal notaFiscal)throws Exception {
		recuperar(notaFiscal.getId());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setNotaFiscal(notaFiscalService.recuperar(id));
	}
	
	private void definirDadosParaIncluirNotaFiscal() throws Exception {
		getTo().getNotaFiscal().setDataCadastro(new Date());
		getTo().getNotaFiscal().setStatus(StatusEnum.A.getKey());
		//PASSAGEM ID USUARIO CADASTRO MANUAL- PROVISÓRIO
		//getTo().getNotaFiscal().getUsuarioCadastro().setId(1);
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaAlterarNotaFiscal() throws Exception {
		getTo().getNotaFiscal().setDataAlteracao(new Date());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaExcluirNotaFiscal(NotaFiscal notaFiscal) throws Exception {
		notaFiscal.setStatus(StatusEnum.E.getKey());
	}

	private void limparObjetoNotaFiscal() throws Exception {
		getTo().setNotaFiscal(null);
	}

}