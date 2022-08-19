package br.com.invest.controleativos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invest.controleativos.controllers.rns.RNRelatorioCalculoMedio;

@RestController
public class RelatorioOperacaoAtivoController {
	
	@Autowired
	private RNRelatorioCalculoMedio rnCalculoMedio;
	
	
	
	@GetMapping("relatorioOperacaoAtivo")
	public void gerarArquivo() {
		rnCalculoMedio.gerarRelatorio();
	}
	
	
	
}