package br.com.invest.controleativos.dtos;

import java.util.Objects;

public class AtivoDTO {
	
	//private static final DecimalFormat formato = new DecimalFormat("###.##");
	
	
	// ####################### DADOS BASICOS
	private String nomeCorretora;
	private String siglaAtivo;
	private String nomeAtivo;
	private String notaFiscal;
	private String dataCompraVenda;
	private String operacaoCompraVenda;
	private String observacao;
	
	// ####################### DADOS QUANTITATIVOS
	
	private Double preco;
	private Integer quantidade;
	
	private Integer quantidadeAcumulada;
	private Double valorAcumulado;
	
	private Double taxaCorretagem;
	private Double taxaOutras;
	private Double taxaIRRF;
	
	private Double percentual;
	
	private boolean ativoAcao;
	
	
	// ####################### DADOS LOGICOS
	
	
	
	// ################################################### METODO GETS

	public String getNomeCorretora() {
		return nomeCorretora;
	}


	public void setNomeCorretora(String nomeCorretora) {
		this.nomeCorretora = nomeCorretora;
	}

	public String getSiglaAtivo() {
		return siglaAtivo;
	}


	public void setSiglaAtivo(String siglaAtivo) {
		this.siglaAtivo = siglaAtivo;
	}

	public String getNomeAtivo() {
		return nomeAtivo;
	}


	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}
	
	public String getNotaFiscal() {
		return notaFiscal;
	}


	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}



	public String getDataCompraVenda() {
		return dataCompraVenda;
	}


	public void setDataCompraVenda(String dataCompraVenda) {
		this.dataCompraVenda = dataCompraVenda;
	}


	public String getOperacaoCompraVenda() {
		return operacaoCompraVenda;
	}


	public void setOperacaoCompraVenda(String operacaoCompraVenda) {
		this.operacaoCompraVenda = operacaoCompraVenda;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public Integer getQuantidadeAcumulada() {
		return quantidadeAcumulada;
	}


	public void setQuantidadeAcumulada(Integer quantidadeAcumulada) {
		this.quantidadeAcumulada = quantidadeAcumulada;
	}
	
	public Double getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(Double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}




	public Double getTaxaCorretagem() {
		return taxaCorretagem;
	}


	public void setTaxaCorretagem(Double taxaCorretagem) {
		this.taxaCorretagem = taxaCorretagem;
	}


	public Double getTaxaOutras() {
		return taxaOutras;
	}


	public void setTaxaOutras(Double taxaOutras) {
		this.taxaOutras = taxaOutras;
	}


	public Double getTaxaIRRF() {
		return taxaIRRF;
	}


	public void setTaxaIRRF(Double taxaIRRF) {
		this.taxaIRRF = taxaIRRF;
	}


	public Double getPercentual() {
		return percentual;
	}


	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}
	
	
	public boolean isAtivoAcao() {
		return ativoAcao;
	}


	public void setAtivoAcao(boolean ativoAcao) {
		this.ativoAcao = ativoAcao;
	}
	
	
	// ################################################### OUTROS METODOS
	


	public Double getValorOperacao () {
		return Double.valueOf(quantidade * preco);
	}
	public Double getPrecoMedio_SemTaxa () {
		
		Double retorno = Double.valueOf(0);
		if (!Objects.isNull(valorAcumulado)) {
			retorno = Double.valueOf(valorAcumulado / quantidadeAcumulada);
		}
		return retorno;
	}
	
//	public Double getPrecoMedio_ComTaxa () {
//		Double retorno = Double.valueOf(0);
//		if (!Objects.isNull(valorAcumuladoComTaxas)) {
//			retorno = Double.valueOf(valorAcumuladoComTaxas / quantidadeAcumulada);
//		}
//		return retorno;
//	}

	

	@Override
	public String toString() {
		return "AtivoDTO [nomeAtivo=" + nomeAtivo
				+ ", quantidade=" + quantidade
				+ ", preco=" + preco
				+ "]";
	}


	
	
		
}