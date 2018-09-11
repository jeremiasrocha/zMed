package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.com.techHouse.zmed.entity.Operadora;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.OperadoraService;
import br.com.techHouse.zmed.to.OperadoraTO;
import br.com.techHouse.zmed.util.UtilArquivo;

@ManagedBean(name = "operadoraController")
@ViewScoped
public class OperadoraController extends ZmedController<OperadoraTO> {

	private static final long serialVersionUID = 536494640048123522L;
	
	private @Inject OperadoraService OperadoraService;

	@PostConstruct
	private void inicializar() {
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setListaOperadora(OperadoraService.pesquisar(getTo()));
			getTo().getListaOperadora();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.operadora.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirOperadora();
		OperadoraService.incluir(getTo().getOperadora());
		adicionarMensagemIncluirSucesso();
		limparObjetoOperadora();
		pesquisar();
		return ZmedNavegacaoEnum.operadora.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarOperadora();
		OperadoraService.alterar(getTo().getOperadora());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoOperadora();
		pesquisar();
		return ZmedNavegacaoEnum.operadora.getName();
	}

	public String excluir(Operadora Operadora)throws Exception {
		definirDadosParaExcluirOperadora(Operadora);
		OperadoraService.excluirOperadora(Operadora);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.operadora.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoOperadora();
		getTo().setListaOperadora(OperadoraService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(Operadora Operadora)throws Exception {
		recuperar(Operadora.getId());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setOperadora(OperadoraService.recuperar(id));
	}
	
	private void definirDadosParaIncluirOperadora() throws Exception {
		getTo().getOperadora().setDataCadastro(new Date());
		getTo().getOperadora().setStatus(StatusEnum.A.getKey());
		getTo().getOperadora().setContrato(UtilArquivo.converteInputStreamEmBytes(getTo().getFileContrato().getInputStream()));
		getTo().getOperadora().setTabelaPrecosContrato(UtilArquivo.converteInputStreamEmBytes(getTo().getFileTabelaPrecos().getInputStream()));
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	public void downloadContrato(Operadora Operadora) throws Exception {
		download(UtilArquivo.converterBytesEmByteArrayOutputStream(Operadora.getContrato()), "Contrato " + Operadora.getNome());
	}
	
	public void downloadTabelaPrecos(Operadora Operadora) throws Exception {
		download(UtilArquivo.converterBytesEmByteArrayOutputStream(Operadora.getTabelaPrecosContrato()), "Tabela " + Operadora.getNome());
	}
	
	private void definirDadosParaAlterarOperadora() throws Exception {
		getTo().getOperadora().setDataAlteracao(new Date());
		//getTo().getNotaFiscal().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
		//		? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaExcluirOperadora(Operadora Operadora) throws Exception {
		Operadora.setStatus(StatusEnum.E.getKey());
	}

	private void limparObjetoOperadora() throws Exception {
		getTo().setOperadora(null);
	}

}