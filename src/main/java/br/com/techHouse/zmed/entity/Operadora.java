package br.com.techHouse.zmed.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the zmed_operadora database table.
 * 
 */

@Entity
@Table(name="zmed_operadora")
public class Operadora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_OPERADORA", sequenceName="zmed_seq_operadora", allocationSize = 1)
	@GeneratedValue(generator="SEQ_OPERADORA",strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="alto_custo")
	private String altoCusto;

	private Integer cep;

	private String cnpj;

	private String complemento;

	private byte[] contrato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_assinatura_contrato")
	private Date dataAssinaturaContrato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_exclusao")
	private Date dataExclusao;

	private String email;

	@Column(name="endereco_completo")
	private String enderecoCompleto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_alteracao")
	private Usuario UsuarioAlteracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario UsuarioCadastro;

	private String nome;

	private String observacao;

	@Column(name="razao_social")
	private String razaoSocial;

	@Column(name="registro_ans")
	private String registroAns;

	private String status;

	@Column(name="tabela_precos_contrato")
	private byte[] tabelaPrecosContrato;

	private String telefone;

	private String uf;

	public Operadora() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAltoCusto() {
		return this.altoCusto;
	}

	public void setAltoCusto(String altoCusto) {
		this.altoCusto = altoCusto;
	}

	public Integer getCep() {
		return this.cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public byte[] getContrato() {
		return this.contrato;
	}

	public void setContrato(byte[] contrato) {
		this.contrato = contrato;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataAssinaturaContrato() {
		return this.dataAssinaturaContrato;
	}

	public void setDataAssinaturaContrato(Date dataAssinaturaContrato) {
		this.dataAssinaturaContrato = dataAssinaturaContrato;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExclusao() {
		return this.dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnderecoCompleto() {
		return this.enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public Usuario getUsuarioAlteracao() {
		return UsuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		UsuarioAlteracao = usuarioAlteracao;
	}

	public Usuario getUsuarioCadastro() {
		return UsuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		UsuarioCadastro = usuarioCadastro;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getRegistroAns() {
		return this.registroAns;
	}

	public void setRegistroAns(String registroAns) {
		this.registroAns = registroAns;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getTabelaPrecosContrato() {
		return this.tabelaPrecosContrato;
	}

	public void setTabelaPrecosContrato(byte[] tabelaPrecosContrato) {
		this.tabelaPrecosContrato = tabelaPrecosContrato;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}