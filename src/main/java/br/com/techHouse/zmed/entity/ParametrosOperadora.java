package br.com.techHouse.zmed.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the zmed_parametros_operadora database table.
 * 
 */

@Entity
@Table(name="zmed_parametros_operadora")
public class ParametrosOperadora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_PARAMETROS_OPERADORA", sequenceName="zmed_seq_parametros_operadora", allocationSize = 1)
	@GeneratedValue(generator="SEQ_PARAMETROS_OPERADORA",strategy=GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Column(name="envio_fatura")
	private String envioFatura;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_operadora")
	private Operadora operadora;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario usuarioCadastro;

	private String pagamento;

	@Column(name="tipo_pagamento")
	private String tipoPagamento;

	@Column(name="utiliza_pacote")
	private String utilizaPacote;

	public ParametrosOperadora() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEnvioFatura() {
		return this.envioFatura;
	}

	public void setEnvioFatura(String envioFatura) {
		this.envioFatura = envioFatura;
	}

	public Operadora getOperadora() {
		return this.operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public Usuario getUsuarioCadastro() {
		return this.usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getTipoPagamento() {
		return this.tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getUtilizaPacote() {
		return this.utilizaPacote;
	}

	public void setUtilizaPacote(String utilizaPacote) {
		this.utilizaPacote = utilizaPacote;
	}

}