package br.com.techHouse.zmed.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the "zMed_usuario" database table.
 * 
 */
@Entity
@Table(name="zmed_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_USUARIO", sequenceName="zmed_seq_usuario", allocationSize = 1)
	@GeneratedValue(generator="SEQ_USUARIO",strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="administrador_local")
	private String administradorLocal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_exclusao")
	private Date dataExclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultimo_acesso")
	private Date dataUltimoAcesso;

	private String email;

	@Column(name="id_prestador")
	private Integer idPrestador;

	private String login;

	private String nome;

	private String perfil;

	private String senha;

	private String status;

	private Integer telefone;

	public Usuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdministradorLocal() {
		return this.administradorLocal;
	}

	public void setAdministradorLocal(String administradorLocal) {
		this.administradorLocal = administradorLocal;
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

	public Date getDataUltimoAcesso() {
		return this.dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdPrestador() {
		return this.idPrestador;
	}

	public void setIdPrestador(Integer idPrestador) {
		this.idPrestador = idPrestador;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTelefone() {
		return this.telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

}