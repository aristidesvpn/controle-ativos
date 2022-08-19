package br.com.invest.controleativos.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TB_CORRETORA") 
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Corretora {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	@Column (nullable = false)
	private String cnpj;
	
	@Column (nullable = false)
	private String sigla;
	
	@Column (name = "razao_social", nullable = false)
	private String razaoSocial;
	
	@Column (name = "data_registro", nullable = false)
	private Timestamp dataRegistro;

	// ################################################### METODO GETS
	
	public Long getCodigo() {
		return codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getSigla() {
		return sigla;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public Timestamp getDataRegistro() {
		return dataRegistro;
	}
}