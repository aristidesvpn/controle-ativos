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
@Table(name = "TB_ITEM_COMPRA_OPERACAO") 
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class ItemCompraOperacao {
	
	public static final String OPERACAO_VENDA = "VENDA";
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	@Column (nullable = false)
	private String descricao;
	
	@Column (name = "data_registro", nullable = false)
	private Timestamp dataRegistro;
	
	

	// ################################################### METODO GETS
	public Long getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Timestamp getDataRegistro() {
		return dataRegistro;
	}



	// ################################################### METODO
	
	public boolean isOperacaoVenda() {
		return ItemCompraOperacao.OPERACAO_VENDA.equals(descricao);
	}

}