package br.com.invest.controleativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invest.controleativos.models.FundoImobiliarioTipo;
import br.com.invest.controleativos.repository.FundoImobiliarioTipoRepository;

@RestController
public class FundoImobiliarioTipoController {
	
	@Autowired
	private FundoImobiliarioTipoRepository dao;
	
	@GetMapping("fundoImobiliarioTipo")
	public List<FundoImobiliarioTipo> consultar() {
		return dao.findAll();
	}

}
