package br.com.techHouse.zmed.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the zmed_nota_fiscal database table.
 * 
 */
@Entity
@Table(name="zmed_nota_fiscal")
public class NotaFiscal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_NOTA_FISCAL", sequenceName="zmed_seq_nota_fiscal", allocationSize = 1)
	@GeneratedValue(generator="SEQ_NOTA_FISCAL",strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="base_calculo_icms")
	private BigDecimal baseCalculoIcms;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_emissao")
	private Date dataEmissao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_exclusao")
	private Date dataExclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_recebimento")
	private Date dataRecebimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_saida")
	private Date dataSaida;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
	private Date dataAlteracao;

	private Integer numero;

	@Column(name="outras_depesas")
	private BigDecimal outrasDepesas;

	private String status;

	private String tipo;

	@Column(name="valor_frete")
	private BigDecimal valorFrete;

	@Column(name="valor_icms")
	private BigDecimal valorIcms;

	@Column(name="valor_ipi")
	private BigDecimal valorIpi;

	@Column(name="valor_seguro")
	private BigDecimal valorSeguro;

	@Column(name="valor_total_nota")
	private BigDecimal valorTotalNota;

	@Column(name="valor_total_produto")
	private BigDecimal valorTotalProduto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_alteracao")
	private Usuario UsuarioAlteracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario UsuarioCadastro;

	public NotaFiscal() {
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

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataExclusao() {
		return this.dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscal other = (NotaFiscal) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public BigDecimal getOutrasDepesas() {
		return outrasDepesas;
	}

	public void setOutrasDepesas(BigDecimal outrasDepesas) {
		this.outrasDepesas = outrasDepesas;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public BigDecimal getValorTotalNota() {
		return valorTotalNota;
	}

	public void setValorTotalNota(BigDecimal valorTotalNota) {
		this.valorTotalNota = valorTotalNota;
	}

	public BigDecimal getValorTotalProduto() {
		return valorTotalProduto;
	}

	public void setValorTotalProduto(BigDecimal valorTotalProduto) {
		this.valorTotalProduto = valorTotalProduto;
	}

}