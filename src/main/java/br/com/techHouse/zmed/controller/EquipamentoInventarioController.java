package br.com.techHouse.zmed.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.techHouse.zmed.entity.EquipamentoInventario;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.EquipamentoInventarioService;
import br.com.techHouse.zmed.service.EquipamentoService;
import br.com.techHouse.zmed.service.NotaFiscalService;
import br.com.techHouse.zmed.to.EquipamentoInventarioTO;

@ManagedBean(name = "equipamentoInventarioController")
@ViewScoped
public class EquipamentoInventarioController extends ZmedController<EquipamentoInventarioTO> {

	private static final long serialVersionUID = -3342772982352322045L;
	
	private @Inject EquipamentoInventarioService equipamentoInventarioService;
	private @Inject EquipamentoService equipamentoService ;
	private @Inject NotaFiscalService notaFiscalService;

	@PostConstruct
	private void inicializar(){
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setListaEquipamentoInventario(equipamentoInventarioService.pesquisar(getTo()));
			getTo().setListaNotaFiscal(notaFiscalService.listar());
			getTo().setListaEquipamento(equipamentoService.listar());
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.equipamentoInventario.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirEquipamentoInventario();
		equipamentoInventarioService.incluir(getTo().getEquipamentoInventario());
		adicionarMensagemIncluirSucesso();
		limparObjetoEquipamentoInventario();
		pesquisar();
		return ZmedNavegacaoEnum.equipamentoInventario.getName();
	}
	
	public String alterar() throws Exception {
		getTo().getEquipamentoInventario();
		definirDadosParaAlterarEquipamentoInventario();
		equipamentoInventarioService.alterar(getTo().getEquipamentoInventario());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoEquipamentoInventario();
		pesquisar();
		return ZmedNavegacaoEnum.equipamentoInventario.getName();
	}

	public String excluir(EquipamentoInventario equipamentoInventario)throws Exception{
		definirDadosParaExcluirEquipamentoInventario(equipamentoInventario);
		equipamentoInventarioService.excluirEquipamentoInventario(equipamentoInventario);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.equipamentoInventario.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoEquipamentoInventario();
		getTo().setListaEquipamentoInventario(equipamentoInventarioService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(EquipamentoInventario equipamentoInventario)throws Exception{
		recuperar(equipamentoInventario.getId());
		/*getTo().getEquipamentoInventario().setTipo(getTo().getEquipamentoInventario().equals(TipoEquipamentoEnum.G.getName())
				? TipoEquipamentoEnum.G.getTipo() : TipoEquipamentoEnum.NG.getTipo());*/
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setEquipamentoInventario(equipamentoInventarioService.recuperar(id));
	}
	
	private void definirDadosParaIncluirEquipamentoInventario() throws Exception {
		getTo().getEquipamentoInventario().setDataCadastro(new Date());
		getTo().getEquipamentoInventario().setStatus(StatusEnum.A.getKey());
		/*getTo().getEquipamentoInventario().setTipo(getTo().getEquipamentoInventario().equals(TipoEquipamentoEnum.G.getName())
				? TipoEquipamentoEnum.G.getTipo() : TipoEquipamentoEnum.NG.getTipo());*/
	}
	
	private void definirDadosParaAlterarEquipamentoInventario() throws Exception {
		getTo().getEquipamentoInventario().setDataAlteracao(new Date());
		/*getTo().getEquipamentoInventario().setTipo(getTo().getEquipamentoInventario().equals(TipoEquipamentoEnum.G.getName())
				? TipoEquipamentoEnum.G.getTipo() : TipoEquipamentoEnum.NG.getTipo());*/
	}
	
	private void definirDadosParaExcluirEquipamentoInventario(EquipamentoInventario equipamentoInventario) throws Exception {
		equipamentoInventario.setStatus(StatusEnum.E.getKey());
	}

	private void limparObjetoEquipamentoInventario() throws Exception {
		getTo().setEquipamentoInventario(null);
	}

}