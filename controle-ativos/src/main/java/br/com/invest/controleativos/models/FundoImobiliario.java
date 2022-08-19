package br.com.invest.controleativos.models;

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
@Table(name = "TB_FUNDO_IMOBILIARIO") 
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class FundoImobiliario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	@Column (nullable = false)
	private String cnpj;
	
	@Column (nullable = false)
	private String sigla;
	
	@Column (nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_tb_fundo_imobiliario_tipo")
	//@Column (name = "codigo_tb_fundo_imobiliario_tipo", nullable = false)
	private FundoImobiliarioTipo codigoFundoImobiliarioTipo;
	
	@Column (name = "data_registro", nullable = false)
	private Timestamp dataRegistro;

	// ################################################### METODO GETS
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FundoImobiliarioTipo getCodigoFundoImobiliarioTipo() {
		return codigoFundoImobiliarioTipo;
	}

	public void setCodigoFundoImobiliarioTipo(FundoImobiliarioTipo codigoFundoImobiliarioTipo) {
		this.codigoFundoImobiliarioTipo = codigoFundoImobiliarioTipo;
	}

	public Timestamp getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Timestamp dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	// ################################################### OUTROS METODOS
	
		public String getSiglaDescricaoFormatada () {
			return this.sigla + " - " + this.descricao;
		}

		
	

}