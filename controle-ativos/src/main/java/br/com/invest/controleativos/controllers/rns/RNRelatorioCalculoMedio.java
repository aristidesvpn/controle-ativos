package br.com.invest.controleativos.controllers.rns;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.invest.controleativos.dtos.AtivoDTO;
import br.com.invest.controleativos.dtos.RelatorioPrecoMedioDTO;
import br.com.invest.controleativos.models.Acoes;
import br.com.invest.controleativos.models.Corretora;
import br.com.invest.controleativos.models.FundoImobiliario;
import br.com.invest.controleativos.models.ItemCompra;
import br.com.invest.controleativos.models.ItemCompraOperacao;
import br.com.invest.controleativos.models.NotaCorretagem;
import br.com.invest.controleativos.repository.AcoesRepository;
import br.com.invest.controleativos.repository.FundoImobiliarioRepository;
import br.com.invest.controleativos.repository.ItemCompraRepository;

@Component
public class RNRelatorioCalculoMedio {
	

	@Autowired
	RNGerarExcel rnGerarExcel;

	@Autowired
	private AcoesRepository daoAcoes;
	@Autowired
	private ItemCompraRepository daoItemCompra;
	@Autowired
	private FundoImobiliarioRepository daoFundoImobiliario;
	
	
	private Integer qntAcumuladaLocal;
	private Double valorAcumuladoLocal;

	
	
	private ItemCompra objItemCompra;
	
	//private AtivoDTO novoObjAtivoDTO;
	private RelatorioPrecoMedioDTO objRelatorioPrecoMedioDTO;
	
	
	List<Acoes> listaRetornoAcoes = null;
	private List<ItemCompra> listaRetornoItemCompra;
	private List<FundoImobiliario> listaRetornoFundoImobiliario;
	
	
	Example<ItemCompra> exemploItemCompra = null;	
	
	
	
	public void gerarRelatorio() {
		
		// declaracao
		// inicializacao
		
		
		// regra de negocio: GERAR DADOS DE ACOES
		this.gerarRelatorioAcoes();
		
		// regra de negocio: GERAR DADOS DE FUNDOS IMBILIARIOS
		this.gerarRelatorioFundoImobiliario();
		
		
	}
	
	private void gerarRelatorioAcoes() {
		
		// declaracao
		
		String nomeRelatorio = null;
		
		// inicializacao
		nomeRelatorio = "_ACOES";
		objRelatorioPrecoMedioDTO = new RelatorioPrecoMedioDTO();
		
		
		// regra de negocio: GERAR DADOS DE ACOES
		listaRetornoAcoes = daoAcoes.findAll(Sort.by(Sort.Direction.ASC, "sigla"));
		
		for (Acoes objRetornoAcoes : listaRetornoAcoes) {
			
			qntAcumuladaLocal = Integer.valueOf(0);
			valorAcumuladoLocal = Double.valueOf(0);

			
			objItemCompra = new ItemCompra();
			objItemCompra.setAcao(objRetornoAcoes);
			
			exemploItemCompra = Example.of(objItemCompra);
			listaRetornoItemCompra = daoItemCompra.findAll(exemploItemCompra, Sort.by(Sort.Direction.ASC, "notaCorretagem.dataNota"));
			

			for (ItemCompra objRetornoItemCompra : listaRetornoItemCompra) {
				var objAtivoDTO = new AtivoDTO();
				this.formatarAtivo(objRetornoItemCompra, objAtivoDTO);
			}
		}
		
		
      	rnGerarExcel.gerarArquivoExcel(objRelatorioPrecoMedioDTO.getListaAtivo(), nomeRelatorio);
				
	}
	
	private void gerarRelatorioFundoImobiliario() {

		
		// declaracao
		
		String nomeRelatorio = null;
		
		// inicializacao
		nomeRelatorio = "_FUNDOS";
		objRelatorioPrecoMedioDTO = new RelatorioPrecoMedioDTO();
		
		
		// regra de negocio: GERAR DADOS DE FUNDOS IMOBILIARIOS
		listaRetornoFundoImobiliario = daoFundoImobiliario.findAll(Sort.by(Sort.Direction.ASC, "sigla"));
		
		for (FundoImobiliario objRetornoFundos : listaRetornoFundoImobiliario) {
			
			qntAcumuladaLocal = Integer.valueOf(0);
			valorAcumuladoLocal = Double.valueOf(0);

			
			objItemCompra = new ItemCompra();
			objItemCompra.setFundoImobiliario(objRetornoFundos);
			
			exemploItemCompra = Example.of(objItemCompra);
			listaRetornoItemCompra = daoItemCompra.findAll(exemploItemCompra, Sort.by(Sort.Direction.ASC, "notaCorretagem.dataNota"));
			

			for (ItemCompra objRetornoItemCompra : listaRetornoItemCompra) {
				var objAtivoDTO = new AtivoDTO();
				this.formatarAtivo(objRetornoItemCompra, objAtivoDTO);
			}
		}
		
		
      	rnGerarExcel.gerarArquivoExcel(objRelatorioPrecoMedioDTO.getListaAtivo(), nomeRelatorio);
				
	
		
	}

	
	
	private AtivoDTO formatarAtivo(ItemCompra pItemCompra, AtivoDTO pAtivoDTO) {
		
		// declaracao
		// inicializacao
		// regra de negocio: metodos
		
		pAtivoDTO = this.carregarDadosBasicos(pItemCompra, pAtivoDTO);
		pAtivoDTO = this.carregarDadosQuantitativos(pItemCompra, pAtivoDTO);
		pAtivoDTO = this.carregarDadosObservacao(pAtivoDTO);
		
		return pAtivoDTO;
		
	}
		
	private AtivoDTO carregarDadosBasicos(ItemCompra pItemCompra, AtivoDTO pAtivoDTO) {
		
		
		// declaracao
		Corretora objCorretora = null;
		ItemCompra objItemCompra = null;
		NotaCorretagem objNotaCorretagem = null;
		
		// inicializacao
		objItemCompra = pItemCompra;
		objNotaCorretagem = objItemCompra.getNotaCorretagem();
		objCorretora = objNotaCorretagem.getCorretora(); 
		
		
		// preenchimento
		pAtivoDTO.setNomeCorretora(objCorretora.getSigla());
		pAtivoDTO.setNotaFiscal(objNotaCorretagem.getNumeroNota());
		pAtivoDTO.setDataCompraVenda(objNotaCorretagem.getDataFormatada());
		pAtivoDTO.setSiglaAtivo(this.obterSiglaAtivo(pItemCompra));
		pAtivoDTO.setNomeAtivo(this.obterSiglaDescricaoAtivo(pItemCompra));
		pAtivoDTO.setAtivoAcao(this.verificarSeAtivoAcao(pItemCompra));
		
		pAtivoDTO.setOperacaoCompraVenda(objItemCompra.getItemCompraOperacao().getDescricao());
		
		
		// retorno
		return pAtivoDTO;
		
	}
	
	private AtivoDTO carregarDadosQuantitativos(ItemCompra pItemCompra, AtivoDTO pAtivoDTO) {
		
		// declaracao
		ItemCompra objItemCompra = null;
		NotaCorretagem objNotaCorretagem = null;
		
		// inicializacao
		objItemCompra = pItemCompra;
		objNotaCorretagem = objItemCompra.getNotaCorretagem();
		
		// preenchimento
		pAtivoDTO.setQuantidade(objItemCompra.getQuantidade());
		pAtivoDTO.setPreco(objItemCompra.getPreco().doubleValue());


		pAtivoDTO.setTaxaIRRF(objNotaCorretagem.getTaxaIrrf().doubleValue());
		pAtivoDTO.setTaxaOutras(objNotaCorretagem.getOutrasTaxas().doubleValue());
		pAtivoDTO.setTaxaCorretagem(objNotaCorretagem.getTaxaCorretagem().doubleValue());
		
		
		
		if (!Objects.isNull(this.qntAcumuladaLocal)) {
			pAtivoDTO.setValorAcumulado(valorAcumuladoLocal);
			pAtivoDTO.setQuantidadeAcumulada(qntAcumuladaLocal);
		}

		
		// percentual
		
		pAtivoDTO = this.calcularPercentualCustoPorItemCompra(pItemCompra.getNotaCorretagem(), pAtivoDTO);
				

		return pAtivoDTO;
		
	}
	
	private AtivoDTO calcularPercentualCustoPorItemCompra(NotaCorretagem pNotaCorretagem, AtivoDTO pAtivoDTO) {
		
		// declaracao
		Double valorTotalNota = null;
		ItemCompra objItemCompra = null;
		List<ItemCompra> listaRetornoItemCompra = null;
				
				
		Example<ItemCompra> exemploItemCompra = null;	
		
		// inicializacao
		valorTotalNota = Double.valueOf(0);
		objItemCompra = new ItemCompra();
		objItemCompra.setNotaCorretagem(pNotaCorretagem);
		
		exemploItemCompra = Example.of(objItemCompra);
		listaRetornoItemCompra = daoItemCompra.findAll(exemploItemCompra);
		for (ItemCompra objRetornoItemCompra : listaRetornoItemCompra) {
			valorTotalNota = valorTotalNota + objRetornoItemCompra.getValorTotalItem().doubleValue();			
		}
		
		if (valorTotalNota.equals(pAtivoDTO.getValorOperacao())) {
			pAtivoDTO.setPercentual(Double.valueOf(1));
		} else {
			pAtivoDTO.setPercentual(Double.valueOf((pAtivoDTO.getValorOperacao() / valorTotalNota)));
		}
		
		
		return pAtivoDTO;
	}
	
	private AtivoDTO carregarDadosObservacao(AtivoDTO pAtivoDTO) {
		
		StringBuilder sbObservacao = new StringBuilder();
		
		if (ItemCompraOperacao.OPERACAO_VENDA.equals(pAtivoDTO.getOperacaoCompraVenda())) {
			
			var qntVendida = pAtivoDTO.getQuantidade();
			var precoMedioCompra = pAtivoDTO.getPrecoMedio_SemTaxa();
			var custoCompra = qntVendida * precoMedioCompra;
			var valorVenda = pAtivoDTO.getValorOperacao();
			
			var lucroPrejuizo = valorVenda - custoCompra;
			
			
			sbObservacao.append("CUSTO COMPRA (qnt vendida * preco medio): ");
			sbObservacao.append("\n");
			sbObservacao.append("Custo compra = ");
			sbObservacao.append(qntVendida);
			sbObservacao.append(" * ");
			sbObservacao.append(String.format("%.4f", precoMedioCompra));
			sbObservacao.append("\n");
			sbObservacao.append("Custo compra = ");
			sbObservacao.append(String.format("%.4f", custoCompra));
			
			sbObservacao.append("\n");
			sbObservacao.append("\n");
			sbObservacao.append("LUCRO / PREJUIZO (valor venda - custo da compra): ");
			sbObservacao.append("\n");
			sbObservacao.append("Lucro / Prejuizo = ");
			sbObservacao.append(String.format("%.4f", valorVenda));
			sbObservacao.append(" * ");
			sbObservacao.append(String.format("%.4f", custoCompra));
			sbObservacao.append("\n");
			sbObservacao.append("Lucro / Prejuizo = ");
			sbObservacao.append(String.format("%.4f", lucroPrejuizo));
			
			sbObservacao.append("\n");
			sbObservacao.append("\n");
			sbObservacao.append("RESULTADO: ");
			if (lucroPrejuizo > 0) {
				sbObservacao.append("LUCRO! Deve-se pagar o imposto de 20%, resultante: ");
				sbObservacao.append(String.format("%.2f", lucroPrejuizo * 0.20));
			} else {
				sbObservacao.append("PREJUIZO! Valor do prejuízo: ");
				sbObservacao.append(String.format("%.2f", lucroPrejuizo));
			}
			

			pAtivoDTO.setObservacao(sbObservacao.toString());

			qntAcumuladaLocal = qntAcumuladaLocal - pAtivoDTO.getQuantidade();
			valorAcumuladoLocal = qntAcumuladaLocal * pAtivoDTO.getPrecoMedio_SemTaxa();


			objRelatorioPrecoMedioDTO.getListaAtivo().add(pAtivoDTO);
			objRelatorioPrecoMedioDTO.getListaAtivo().add(this.incluirResultanteParcial(pAtivoDTO));	

		} else {
			
			
			qntAcumuladaLocal = qntAcumuladaLocal + pAtivoDTO.getQuantidade();
			valorAcumuladoLocal = valorAcumuladoLocal + pAtivoDTO.getValorOperacao();
			
			
			pAtivoDTO.setValorAcumulado(valorAcumuladoLocal);
			pAtivoDTO.setQuantidadeAcumulada(qntAcumuladaLocal);
			
			objRelatorioPrecoMedioDTO.getListaAtivo().add(pAtivoDTO);
		}
		
		return pAtivoDTO;
		
	}

	private String obterSiglaAtivo(ItemCompra pItemCompra) {
		
		
		var retorno = String.valueOf("??? SIGLA DO ATIVO NAO ENCONTRADA ???");
		
		if (Objects.isNull(pItemCompra.getAcao())) {
			retorno = pItemCompra.getFundoImobiliario().getSigla();
		} else {
			retorno = pItemCompra.getAcao().getSigla();
		}
		
		return retorno;
		
	}
	
	private String obterSiglaDescricaoAtivo(ItemCompra pItemCompra) {
		
		
		var retorno = String.valueOf("??? NOME DO ATIVO NAO ENCONTRADO ???");
		
		if (Objects.isNull(pItemCompra.getAcao())) {
			retorno = pItemCompra.getFundoImobiliario().getSiglaDescricaoFormatada();
		} else {
			retorno = pItemCompra.getAcao().getSiglaDescricaoFormatada();
		}
		
		return retorno;
		
	}
	
	private Boolean verificarSeAtivoAcao(ItemCompra pItemCompra) {
		
		
		Boolean retorno = null;
		
		if (Objects.isNull(pItemCompra.getAcao())) {
			retorno = Boolean.FALSE;
		} else {
			retorno = Boolean.TRUE;
		}
		
		return retorno;
		
	}
	
	private AtivoDTO incluirResultanteParcial(AtivoDTO pAtivoDTO) {
		
		// declaracoes
		AtivoDTO objResultante = null;
		
		// inicializacao
		objResultante = new AtivoDTO();
		objResultante.setSiglaAtivo(pAtivoDTO.getSiglaAtivo());
		objResultante.setOperacaoCompraVenda(">>>>> Posição restante :: ");
		objResultante.setQuantidade(this.qntAcumuladaLocal);
		
		if (objResultante.getQuantidade() > 1) {
			objResultante.setPreco(pAtivoDTO.getPrecoMedio_SemTaxa());
			objResultante.setValorAcumulado(this.valorAcumuladoLocal);
			objResultante.setQuantidadeAcumulada(this.qntAcumuladaLocal);
		} else {
		}
		
		return objResultante;
		
	}
	

}