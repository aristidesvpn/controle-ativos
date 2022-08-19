package br.com.invest.controleativos.dtos;

import java.util.ArrayList;
import java.util.List;

public class RelatorioPrecoMedioDTO {
	
	private String ano;
	
	
	private List<AtivoDTO> listaAtivo;
	
	
	
	
	public RelatorioPrecoMedioDTO() {
		super();
		this.ano = null;
		this.listaAtivo = new ArrayList<AtivoDTO>();
	}
	
	
	
	public String getAno() {
		return ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	

	
	public List<AtivoDTO> getListaAtivo() {
		return listaAtivo;
	}
	public void setListaAtivo(List<AtivoDTO> listaAtivo) {
		this.listaAtivo = listaAtivo;
	}	
	
	

}
