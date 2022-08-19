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
@Table(name = "TB_FUNDO_IMOBILIARIO_TIPO") 
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class FundoImobiliarioTipo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long codigo;
		
	@Column (nullable = false)
	private String descricao;
	
	@Column (name = "data_registro", nullable = false)
	private Timestamp dataRegistro;

}