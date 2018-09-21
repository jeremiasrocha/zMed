package br.com.techHouse.zmed.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import br.com.techHouse.zmed.entity.Equipamento;
import br.com.techHouse.zmed.enums.StatusEnum;
import br.com.techHouse.zmed.enums.ZmedNavegacaoEnum;
import br.com.techHouse.zmed.service.EquipamentoService;
import br.com.techHouse.zmed.to.EquipamentoTO;

@ManagedBean(name = "equipamentoController")
@ViewScoped
public class EquipamentoController extends ZmedController<EquipamentoTO> {

	private static final long serialVersionUID = 3843242332502167201L;

	private @Inject EquipamentoService equipamentoService;

	@PostConstruct
	private void inicializar() {
		try {
			if (getRequest().getParameter("parametro") != null) {
				if (getRequest().getParameter("parametro").equals("new")) {
				} else if (getRequest().getParameter("parametro").equals("edit")) {
					recuperar(Integer.valueOf(getRequest().getParameter("id")));
				}
			}
			getTo().setEquipamentos(equipamentoService.pesquisar(getTo()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String abrirTela() throws Exception {
		return ZmedNavegacaoEnum.equipamento.getName();
	}

	public String incluir() throws Exception {
		definirDadosParaIncluirEquipamento();
		equipamentoService.incluir(getTo().getEquipamento());
		adicionarMensagemIncluirSucesso();
		limparObjetoEquipamento();
		pesquisar();
		return ZmedNavegacaoEnum.equipamento.getName();
	}

	public String alterar() throws Exception {
		definirDadosParaAlterarEquipamento();
		equipamentoService.alterar(getTo().getEquipamento());
		adicionarMensagemAlteracaoSucesso();
		limparObjetoEquipamento();
		pesquisar();
		return ZmedNavegacaoEnum.equipamento.getName();
	}

	public String excluir(Equipamento equipamento) throws Exception {
		definirDadosParaExcluirEquipamento(equipamento);
		equipamentoService.excluirEquipamento(equipamento);
		adicionarMensagemExclusaoSucesso();
		pesquisar();
		return ZmedNavegacaoEnum.equipamento.getName();
	}

	public void pesquisar() throws Exception {
		getTo().setEquipamentos(equipamentoService.pesquisar(getTo()));
	}
	
	public void listar() throws Exception {
		limparObjetoEquipamento();
		getTo().setEquipamentos(equipamentoService.pesquisar(getTo()));
	}

	public void recuperarCompletoPorId(Equipamento equipamento) throws Exception {
		recuperar(equipamento.getId());
	}

	private void recuperar(Integer id) throws Exception {
		getTo().setEquipamento(equipamentoService.recuperar(id));
	}

	private void definirDadosParaIncluirEquipamento() throws Exception {
		getTo().getEquipamento().setDataCadastro(new Date());
		getTo().getEquipamento().setStatus(StatusEnum.A.getKey());
		getTo().getEquipamento().setCodigo(definirCodigoEquipamento());
		// PASSAGEM ID USUARIO CADASTRO MANUAL- PROVISÓRIO
		// getTo().getEquipamento().getUsuarioCadastro().setId(1);
		/*
		 * getTo().getEquipamento().setTipo(getTo().getTipoEquipamentoString().equals(
		 * TipoEquipamentoEnum.G.getName()) ? TipoEquipamentoEnum.G.getTipo() :
		 * TipoEquipamentoEnum.NG.getTipo());
		 */
	}

	private void definirDadosParaAlterarEquipamento() throws Exception {
		getTo().getEquipamento().setDataAlteracao(new Date());
	}

	private void definirDadosParaExcluirEquipamento(Equipamento equipamento) throws Exception {
		equipamento.setStatus(StatusEnum.E.getKey());
	}

	private Integer definirCodigoEquipamento() throws Exception {
		return equipamentoService.definirCodigoEquipamento();
	}

	private void limparObjetoEquipamento() throws Exception {
		getTo().setEquipamento(null);
	}

}