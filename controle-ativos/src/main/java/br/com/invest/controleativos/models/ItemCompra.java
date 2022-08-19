package br.com.invest.controleativos.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TB_ITEM_COMPRA") 
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class ItemCompra {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	@Column (nullable = false)
	private Integer quantidade;
	
	@Column (nullable = false)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "codigo_tb_acoes")
	//@Column (name = "codigo_tb_acoes", nullable = false)
	private Acoes acao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_tb_fundo_imobiliario")
	//@Column (name = "codigo_tb_fundo_imobiliario", nullable = false)
	private FundoImobiliario fundoImobiliario;
	
	@ManyToOne
	@JoinColumn(name = "codigo_tb_item_compra_operacao")
	//@Column (name = "codigo_tb_item_compra_operacao", nullable = false)
	private ItemCompraOperacao itemCompraOperacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_tb_nota_corretagem")
	//@Column (name = "codigo_tb_nota_corretagem", nullable = false)
	private NotaCorretagem notaCorretagem;
	
	@Column (name = "data_registro", nullable = false)
	private Timestamp dataRegistro;
	
	// ################################################### CONSTRUTOR
	
	
	// ################################################### METODO GETS

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Acoes getAcao() {
		return acao;
	}

	public void setAcao(Acoes codigoAcoes) {
		this.acao = codigoAcoes;
	}

	public FundoImobiliario getFundoImobiliario() {
		return fundoImobiliario;
	}

	public void setFundoImobiliario(FundoImobiliario codigoFundoImobiliario) {
		this.fundoImobiliario = codigoFundoImobiliario;
	}

	public ItemCompraOperacao getItemCompraOperacao() {
		return itemCompraOperacao;
	}

	public void setItemCompraOperacao(ItemCompraOperacao codigoItemCompraOperacao) {
		this.itemCompraOperacao = codigoItemCompraOperacao;
	}

	public NotaCorretagem getNotaCorretagem() {
		return notaCorretagem;
	}

	public void setNotaCorretagem(NotaCorretagem codigoNotaCorretagem) {
		this.notaCorretagem = codigoNotaCorretagem;
	}

	public Timestamp getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Timestamp dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	// ################################################### OUTROS METODOS
	
	public BigDecimal getValorTotalItem() {
		return BigDecimal.valueOf(quantidade.intValue() * preco.doubleValue());
	}

	

}