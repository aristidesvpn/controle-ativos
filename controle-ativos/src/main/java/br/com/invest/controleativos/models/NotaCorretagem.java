package br.com.invest.controleativos.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Table(name = "TB_NOTA_CORRETAGEM") 
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class NotaCorretagem {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	@Column (name = "numero_nota", nullable = false)
	private String numeroNota;
	
	@Column (name = "data_nota", nullable = false)
	private Date dataNota;
	
	@Column (name = "taxa_liquidacao", nullable = false)
	private BigDecimal taxaLiquidacao;

	@Column (name = "taxa_registro", nullable = false)
	private BigDecimal taxaRegistro;
	
	@Column (name = "taxa_corretagem", nullable = false)
	private BigDecimal taxaCorretagem;
	
	@Column (name = "taxa_iss", nullable = false)
	private BigDecimal taxaIss;
	
	@Column (name = "taxa_termo_opcoes", nullable = false)
	private BigDecimal taxaTermoOpcoes;
	
	@Column (name = "taxa_ana", nullable = false)
	private BigDecimal taxaAna;
	
	@Column (name = "taxa_emolumentos", nullable = false)
	private BigDecimal taxaEmolumentos;
	
	@Column (name = "taxa_irrf", nullable = false)
	private BigDecimal taxaIrrf;
	
	@ManyToOne
	@JoinColumn(name = "codigo_tb_corretora")
	//@Column (name = "codigo_tb_corretora", nullable = false)
	private Corretora corretora;
	
	@Column (name = "data_registro", nullable = false)
	private Timestamp dataRegistro;
	
	
	// ################################################### METODO GETS

	public Long getCodigo() {
		return codigo;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public Date getDataNota() {
		return dataNota;
	}

	public BigDecimal getTaxaLiquidacao() {
		return taxaLiquidacao;
	}

	public BigDecimal getTaxaRegistro() {
		return taxaRegistro;
	}

	public BigDecimal getTaxaCorretagem() {
		return taxaCorretagem;
	}

	public BigDecimal getTaxaIss() {
		return taxaIss;
	}

	public BigDecimal getTaxaTermoOpcoes() {
		return taxaTermoOpcoes;
	}

	public BigDecimal getTaxaAna() {
		return taxaAna;
	}

	public BigDecimal getTaxaEmolumentos() {
		return taxaEmolumentos;
	}

	public BigDecimal getTaxaIrrf() {
		return taxaIrrf;
	}

	public Corretora getCorretora() {
		return corretora;
	}

	public Timestamp getDataRegistro() {
		return dataRegistro;
	}
	
	
	// ################################################### METODO
	
	public String getAnoFormatado() {
		return new SimpleDateFormat("YYYY").format(this.dataNota);
	}
	
	public String getDataFormatada() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.dataNota);
	}
	
	public BigDecimal getOutrasTaxas() {
		return BigDecimal.valueOf(
				taxaLiquidacao.doubleValue() 
				+ taxaRegistro.doubleValue()
				+ taxaIss.doubleValue()
				+ taxaTermoOpcoes.doubleValue()
				+ taxaAna.doubleValue()
				+ taxaEmolumentos.doubleValue()
				);
	}
	

}