package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.com.techHouse.zmed.entity.Medicamento;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.TipoMedicamentoEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.MedicamentoService;
import br.com.techHouse.zmed.to.MedicamentoTO;

@ManagedBean(name = "medicamentoController")
@ViewScoped
public class MedicamentoController extends ZmedController<MedicamentoTO> {

	private static final long serialVersionUID = 3843242332502167201L;
	
	private @Inject MedicamentoService medicamentoService;

	@PostConstruct
	private void inicializar() {
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
			getTo().setMedicamentos(medicamentoService.pesquisar(getTo()));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.medicamento.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirMedicamento();
		medicamentoService.incluir(getTo().getMedicamento());
		adicionarMensagemIncluirSucesso();
		limparObjetoMedicamento();
		pesquisar();
		return ZmedNavegacaoEnum.medicamento.getName();
	}
	
	public String alterar() throws Exception {
		definirDadosParaAlterarMedicamento();
		medicamentoService.alterar(getTo().getMedicamento());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoMedicamento();
		pesquisar();
		return ZmedNavegacaoEnum.medicamento.getName();
	}

	public String excluir(Medicamento medicamento)throws Exception {
		definirDadosParaExcluirMedicamento(medicamento);
		medicamentoService.excluirMedicamento(medicamento);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.medicamento.getName();
	}
	
	public void pesquisar() throws Exception {
		getTo().getMedicamento();
		//limparObjetoMedicamento();
		getTo().setMedicamentos(medicamentoService.pesquisar(getTo()));
	}
	
	public void recuperarCompletoPorId(Medicamento medicamento)throws Exception {
		recuperar(medicamento.getId());
		getTo().getMedicamento().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
				? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setMedicamento(medicamentoService.recuperar(id));
	}
	
	private void definirDadosParaIncluirMedicamento() throws Exception {
		getTo().getMedicamento().setDataCadastro(new Date());
		getTo().getMedicamento().setStatus(StatusEnum.A.getKey());
		getTo().getMedicamento().setCodigo(definirCodigoMedicamento());
		//PASSAGEM ID USUARIO CADASTRO MANUAL- PROVISÓRIO
		//getTo().getMedicamento().getUsuarioCadastro().setId(1);
		getTo().getMedicamento().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
				? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaAlterarMedicamento() throws Exception {
		getTo().getMedicamento().setDataAlteracao(new Date());
		getTo().getMedicamento().setTipo(getTo().getTipoMedicamentoString().equals(TipoMedicamentoEnum.G.getName())
				? TipoMedicamentoEnum.G.getTipo() : TipoMedicamentoEnum.NG.getTipo());
	}
	
	private void definirDadosParaExcluirMedicamento(Medicamento medicamento) throws Exception {
		medicamento.setStatus(StatusEnum.E.getKey());
	}
	
	private Integer definirCodigoMedicamento() throws Exception {
		return medicamentoService.definirCodigoMedicamento();
	}

	private void limparObjetoMedicamento() throws Exception {
		getTo().setMedicamento(null);
	}

}