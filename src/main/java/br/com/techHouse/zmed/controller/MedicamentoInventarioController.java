package br.com.techHouse.zmed.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.techHouse.zmed.entity.MedicamentoInventario;
import br.com.techHouse.zmed.enums.FracionamentoMedicamentoEnum;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.MedicamentoInventarioService;
import br.com.techHouse.zmed.service.MedicamentoService;
import br.com.techHouse.zmed.service.NotaFiscalService;
import br.com.techHouse.zmed.to.MedicamentoInventarioTO;

@ManagedBean(name = "medicamentoInventarioController")
@ViewScoped
public class MedicamentoInventarioController extends ZmedController<MedicamentoInventarioTO> {

	private static final long serialVersionUID = 6032258080883471077L;
	
	private @Inject MedicamentoInventarioService medicamentoInventarioService;
	private @Inject MedicamentoService medicamentoService ;
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
			getTo().setListaMedicamentoInventario(medicamentoInventarioService.pesquisar(getTo()));
			getTo().setListaNotaFiscal(notaFiscalService.listar());
			getTo().setListaMedicamento(medicamentoService.listar());
			
			
			getTo().getListaMedicamentoInventario();
			getTo().getListaNotaFiscal();
			getTo().getListaMedicamento();
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.medicamentoInventario.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirMedicamentoInventario();
		medicamentoInventarioService.incluir(getTo().getMedicamentoInventario());
		adicionarMensagemIncluirSucesso();
		limparObjetoMedicamentoInventario();
		pesquisar();
		return ZmedNavegacaoEnum.medicamentoInventario.getName();
	}
	
	public String alterar() throws Exception {
		getTo().getMedicamentoInventario();
		definirDadosParaAlterarMedicamentoInventario();
		medicamentoInventarioService.alterar(getTo().getMedicamentoInventario());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoMedicamentoInventario();
		pesquisar();
		return ZmedNavegacaoEnum.medicamentoInventario.getName();
	}

	public String excluir(MedicamentoInventario medicamentoInventario)throws Exception{
		definirDadosParaExcluirMedicamentoInventario(medicamentoInventario);
		medicamentoInventarioService.excluirMedicamentoInventario(medicamentoInventario);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.medicamentoInventario.getName();
	}
	
	public void pesquisar() throws Exception {
		limparObjetoMedicamentoInventario();
		getTo().setListaMedicamentoInventario(medicamentoInventarioService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(MedicamentoInventario medicamentoInventario)throws Exception{
		recuperar(medicamentoInventario.getId());
		/*getTo().getMedicamentoInventario().setTipo(getTo().getMedicamentoInventario().equals(TipoMedicamentoEnum.G.getName())
				? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());*/
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setMedicamentoInventario(medicamentoInventarioService.recuperar(id));
	}
	
	private void definirDadosParaIncluirMedicamentoInventario() throws Exception {
		getTo().getMedicamentoInventario().setDataCadastro(new Date());
		getTo().getMedicamentoInventario().setStatus(StatusEnum.A.getKey());
		/*getTo().getMedicamentoInventario().setTipo(getTo().getMedicamentoInventario().equals(TipoMedicamentoEnum.G.getName())
				? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());*/
	}
	
	private void definirDadosParaAlterarMedicamentoInventario() throws Exception {
		getTo().getMedicamentoInventario().setDataAlteracao(new Date());
		/*getTo().getMedicamentoInventario().setTipo(getTo().getMedicamentoInventario().equals(TipoMedicamentoEnum.G.getName())
				? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());*/
	}
	
	public FracionamentoMedicamentoEnum[] getFracionamentoMedicamentoEnum()throws Exception{
		return FracionamentoMedicamentoEnum.values();
	}
	
	private void definirDadosParaExcluirMedicamentoInventario(MedicamentoInventario medicamentoInventario) throws Exception {
		medicamentoInventario.setStatus(StatusEnum.E.getKey());
	}

	private void limparObjetoMedicamentoInventario() throws Exception {
		getTo().setMedicamentoInventario(null);
	}

}