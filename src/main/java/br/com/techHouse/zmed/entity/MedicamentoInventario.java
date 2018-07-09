package br.com.techHouse.zmed.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the zmed_medicamento_inventario database table.
 * 
 */
@Entity
@Table(name="zmed_medicamento_inventario")
public class MedicamentoInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_MEDICAMENTO_INVENTARIO", sequenceName="zmed_seq_medicamento_inventario", allocationSize = 1)
	@GeneratedValue(generator="SEQ_MEDICAMENTO_INVENTARIO",strategy=GenerationType.AUTO)
	private Integer id;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_exclusao")
	private Date dataExclusao;
	
	@Column(name="qtd_fracionamento")
	private Integer qtdFracionamento;

	private Integer quantidade;

	@Column(name="tipo_fracionamento")
	private String tipoFracionamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_medicamento")
	private Medicamento medicamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_nota_fiscal")
	private NotaFiscal notaFiscal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_alteracao")
	private Usuario UsuarioAlteracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario UsuarioCadastro;

	public MedicamentoInventario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getQtdFracionamento() {
		return this.qtdFracionamento;
	}

	public void setQtdFracionamento(Integer qtdFracionamento) {
		this.qtdFracionamento = qtdFracionamento;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipoFracionamento() {
		return this.tipoFracionamento;
	}

	public void setTipoFracionamento(String tipoFracionamento) {
		this.tipoFracionamento = tipoFracionamento;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}