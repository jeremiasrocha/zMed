package br.com.techHouse.zmed.enums;

public enum ZmedMensagemEnum {
	
	//MENSAGENS GENÉRICAS
	INCLUIR_SUCESSO("label.msg.incluirSucesso","label.msg.incluirSucesso"),
	INCLUIR_ERRO("label.msg.incluirErro","label.msg.incluirErro"),
	ALTERAR_SUCESSO("label.msg.alterarSucesso","label.msg.alterarSucesso"),
	ALTERAR_ERRO("label.msg.alterarErro","label.msg.alterarErro"),
	EXCLUIR_SUCESSO("label.msg.excluirSucesso","label.msg.excluirSucesso"),
	EXCLUIR_ERRO("label.msg.excluirErro","label.msg.excluirErro"),
	ACESSO_NEGADO("label.msg.acessoNegado","label.msg.acessoNegado"),
	REGISTRO_JA_CADASTRADO("label.msg.registroJaCadastrado","label.msg.registroJaCadastrado"),

	//MENSAGENS USUÁRIO
	UC_USUARIO_SENHA_ALTERADA_SUCESSO("label.uc.usuario.msg.senhaAlteradaComSucesso","label.uc.usuario.msg.senhaAlteradaComSucesso"),
	UC_USUARIO_NAO_ENCONTRADO("label.uc.usuario.msg.usuarioNaoEncontrado","label.uc.usuario.msg.usuarioNaoEncontrado"),
	UC_USUARIO_LOGIN_JA_CADASTRADO("label.uc.usuario.msg.loginJaCadastrado","label.uc.usuario.msg.loginJaCadastrado"),
	ERROR_CONFIRM_LOGIN("label.uc.usuario.erro.senhaconfirmacao","label.uc.usuario.erro.senhaconfirmacao"),
	ERROR_LOGIN("label.uc.usuario.msg.ErroLogin","label.uc.usuario.msg.ErroLogin"),
	
	//MENSAGENS MEDICAMENTO
	UC_MEDICAMENTO_NAO_ENCONTRADO("label.uc.medicamento.msg.medicamentoNaoEncontrado","label.uc.medicamento.msg.medicamentoNaoEncontrado"),
	UC_MEDICAMENTO_JA_CADASTRADO("label.uc.medicamento.msg.medicamentoJaCadastrado","label.uc.medicamento.msg.medicamentoJaCadastrado"),
	
	//MENSAGENS MEDICAMENTO INVENTÁRIO
	UC_MEDICAMENTO_INVENTARIO_NAO_ENCONTRADO("label.uc.medicamentoInventario.msg.medicamentoNaoEncontrado","label.uc.medicamentoInventario.msg.medicamentoNaoEncontrado"),
	UC_MEDICAMENTO_INVENTARIO_JA_CADASTRADO("label.uc.medicamentoInventario.msg.medicamentoJaCadastrado","label.uc.medicamentoInventario.msg.medicamentoJaCadastrado"),
	
	//MENSAGENS NOTA FISCAL
	UC_NOTA_FISCAL_NAO_ENCONTRADA("label.uc.notaFiscal.msg.notaFiscalNaoEncontrada","label.uc.notaFiscal.msg.notaFiscalNaoEncontrada"),
	UC_NOTA_FISCAL_JA_CADASTRADA("label.uc.notaFiscal.msg.notaFiscalJaCadastrada","label.uc.notaFiscal.msg.notaFiscalJaCadastrada"),
	
	//MENSAGENS FORNECEDOR
	UC_FORNECEDOR_NAO_ENCONTRADO("label.uc.fornecedor.msg.fornecedorNaoEncontrado","label.uc.fornecedor.msg.fornecedorNaoEncontrado"),
	UC_FORNECEDOR_JA_CADASTRADO("label.uc.fornecedor.msg.fornecedorJaCadastrado","label.uc.fornecedor.msg.fornecedorJaCadastrado");
	
	private ZmedMensagemEnum(String key,String text){
		this.key = key;
		this.text = text;
	}
	
	private String key;
	private String text;

	public String getText() {
		return text;
	}

	public String getKey() {
		return key;
	}

}