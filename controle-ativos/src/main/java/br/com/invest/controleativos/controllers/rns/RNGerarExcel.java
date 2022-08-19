package br.com.invest.controleativos.controllers.rns;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import br.com.invest.controleativos.dtos.AtivoDTO;


@Component
public class RNGerarExcel {
	
	
	private Integer linhaPosicao;
	private Integer colunaPosicao;
	
	
	private Row linhaPlanilha;
	private CellStyle cellStyle;
	private HSSFSheet sheetAtivo;
	private HSSFWorkbook workbook;
	
	
	private FileOutputStream out;
	

	public void gerarArquivoExcel(List<AtivoDTO> pLista, String pNomeRelatorio) {
		
		String nomeAba = null;
		
		workbook = new HSSFWorkbook();
		cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
			
		try {
			
			for (AtivoDTO ativoDTO : pLista) {
				
					if (
							Objects.isNull(nomeAba)
							|| !nomeAba.equals(ativoDTO.getSiglaAtivo())
							) {
						
						nomeAba = ativoDTO.getSiglaAtivo();
						
						this.incluirNovaAbaNovoCabecalho(nomeAba);
						this.incluirRegistroAtivo(ativoDTO);
						
					} else {
						this.incluirRegistroAtivo(ativoDTO);
					}
					
			}
			
			 out = new FileOutputStream("relatorioConsolidado" + pNomeRelatorio + ".xlsx");

	        workbook.write(out);
	        out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void incluirNovaAbaNovoCabecalho(String pDescricaoAba) {
		
		// declaracoes
		// instacializacao
		linhaPosicao = Integer.valueOf(0);
		colunaPosicao = Integer.valueOf(0);
		
		sheetAtivo = workbook.createSheet(pDescricaoAba);
		
		// regra de negocio
		
		linhaPlanilha = sheetAtivo.createRow(linhaPosicao++);
		this.incluirColuna("CORRETORA");
		this.incluirColuna("DATA OPERACAO");
		this.incluirColuna("NOTA FISCAL");
		this.incluirColuna("ATIVO");
		this.incluirColuna("OPERACAO");
		this.incluirColuna("QNT");
		this.incluirColuna("PRECO");
		this.incluirColuna("VALOR (Qnt x Preco)");
		this.incluirColuna("PM Sem TAXA");
		
		this.incluirColuna("Qnt Acumulada");
		this.incluirColuna("Valor Acumulado (Qnt Acumulada x PM Sem Taxa)");

		this.incluirColuna("Tx Corretagem");
		this.incluirColuna("Tx Outras");
		this.incluirColuna("Tx IRRF");
		this.incluirColuna("% Custo do Item");
		//this.incluirColuna("PM Com TAXA");
		this.incluirColuna("Observacao");
		
		
		
	}
	
	private void incluirRegistroAtivo(AtivoDTO pAtivoDTO) {
		
		colunaPosicao = Integer.valueOf(0);
		linhaPlanilha = sheetAtivo.createRow(linhaPosicao++);
		
		if (!Objects.isNull(pAtivoDTO)) {
			
			this.incluirColuna(pAtivoDTO.getNomeCorretora());
			this.incluirColuna(pAtivoDTO.getDataCompraVenda());
			this.incluirColuna(pAtivoDTO.getNotaFiscal());
			this.incluirColuna(pAtivoDTO.getNomeAtivo());
			this.incluirColuna(pAtivoDTO.getOperacaoCompraVenda());
			this.incluirColuna(pAtivoDTO.getQuantidade().toString());
			
			if (!Objects.isNull(pAtivoDTO.getPreco())) {
				this.incluirColuna(String.format("%.2f", pAtivoDTO.getPreco()));
				this.incluirColuna(String.format("%.2f", pAtivoDTO.getValorOperacao()));
				this.incluirColuna(String.format("%.4f", pAtivoDTO.getPrecoMedio_SemTaxa()));
				
				//------------------------- 
				this.incluirColuna(String.valueOf(pAtivoDTO.getQuantidadeAcumulada()));
				this.incluirColuna(String.format("%.4f", pAtivoDTO.getValorAcumulado()));

				//------------------------- 
				this.incluirColuna(String.format("%.2f", pAtivoDTO.getTaxaCorretagem()));
				this.incluirColuna(String.format("%.2f", pAtivoDTO.getTaxaOutras()));
				this.incluirColuna(String.format("%.2f", pAtivoDTO.getTaxaIRRF()));
				if (!Objects.isNull(pAtivoDTO.getPercentual())) {
					this.incluirColuna(String.format("%.2f", pAtivoDTO.getPercentual() * 100));
				} else {
					this.incluirColuna("");
				}
				//this.incluirColuna(String.format("%.2f", pAtivoDTO.getPrecoMedioCompra_ComTaxa()));
				/*
				//------------------------- 
				this.incluirColuna("");
				this.incluirColuna(String.format("%.2f", pAtivoDTO.getValorAcumuladoCompraComTaxas()));
				*/
			}
			this.incluirColuna(pAtivoDTO.getObservacao(), Boolean.TRUE);
		}
		
		


	}
	
	private void incluirColuna(String pDado) {
		this.incluirColuna(pDado, Boolean.FALSE);
	}
	private void incluirColuna(String pDado, boolean pIsObs) {
		
		Cell colunaPlanilha = linhaPlanilha.createCell(colunaPosicao++);
		colunaPlanilha.setCellValue(pDado);
		
		if (pIsObs) {
			colunaPlanilha.setCellStyle(cellStyle);	
		}		
	}
			
}
