package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.com.techHouse.zmed.entity.Fornecedor;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.FornecedorService;
import br.com.techHouse.zmed.to.FornecedorTO;

@ManagedBean(name = "fornecedorController")
@ViewScoped
public class OperadoraController extends ZmedController<FornecedorTO> {

	private static final long serialVersionUID = 996572960606200534L;
	
	private @Inject FornecedorService fornecedorService;

	@PostConstruct
	private void inicializar() {
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setListaFornecedor(fornecedorService.pesquisar(getTo()));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.fornecedor.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirFornecedor();
		fornecedorService.incluir(getTo().getFornecedor());
		adicionarMensagemIncluirSucesso();
		limparObjetoFornecedor();
		pesquisar();
		return ZmedNavegacaoEnum.fornecedor.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarFornecedor();
		fornecedorService.alterar(getTo().getFornecedor());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoFornecedor();
		pesquisar();
		return ZmedNavegacaoEnum.fornecedor.getName();
	}

	public String excluir(Fornecedor fornecedor)throws Exception {
		definirDadosParaExcluirFornecedor(fornecedor);
		fornecedorService.excluirFornecedor(fornecedor);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.fornecedor.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoFornecedor();
		getTo().setListaFornecedor(fornecedorService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(Fornecedor fornecedor)throws Exception {
		recuperar(fornecedor.getId());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setFornecedor(fornecedorService.recuperar(id));
	}
	
	private void definirDadosParaIncluirFornecedor() throws Exception {
		getTo().getFornecedor().setDataCadastro(new Date());
		getTo().getFornecedor().setStatus(StatusEnum.A.getKey());
		getTo().getFornecedor();
		//PASSAGEM ID USUARIO CADASTRO MANUAL- PROVISÓRIO
		//getTo().getNotaFiscal().getUsuarioCadastro().setId(1);
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaAlterarFornecedor() throws Exception {
		getTo().getFornecedor().setDataAlteracao(new Date());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaExcluirFornecedor(Fornecedor fornecedor) throws Exception {
		fornecedor.setStatus(StatusEnum.E.getKey());
	}

	private void limparObjetoFornecedor() throws Exception {
		getTo().setFornecedor(null);
	}

}