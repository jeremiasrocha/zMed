package br.com.techHouse.zmed.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the zmed_prestador database table.
 * 
 */
@Entity
@Table(name="zmed_prestador")
public class Prestador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_PRESTADOR", sequenceName="zmed_seq_prestador", allocationSize = 1)
	@GeneratedValue(generator="SEQ_PRESTADOR",strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="alto_custo")
	private String altoCusto;

	private Integer cep;

	private String cnes;

	private String codigo;

	@Column(name="codigo_ibge")
	private String codigoIbge;

	private String complemento;

	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Column(name="data_exclusao")
	private Date dataExclusao;

	private String email;

	@Column(name="endereco_completo")
	private String enderecoCompleto;

	private String nome;

	private String observacao;

	@Column(name="razao_social")
	private String razaoSocial;

	private String status;

	private String telefone;

	private String tipo;

	@Column(name="tipo_pessoa")
	private String tipoPessoa;

	private String uf;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_alteracao")
	private Usuario UsuarioAlteracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario UsuarioCadastro;

	public Prestador() {
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

	public String getCnes() {
		return this.cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoIbge() {
		return this.codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoPessoa() {
		return this.tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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

}