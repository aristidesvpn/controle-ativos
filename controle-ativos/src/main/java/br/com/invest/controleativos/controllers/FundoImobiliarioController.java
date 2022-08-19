package br.com.invest.controleativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invest.controleativos.models.FundoImobiliario;
import br.com.invest.controleativos.repository.FundoImobiliarioRepository;

@RestController
public class FundoImobiliarioController {
	
	@Autowired
	private FundoImobiliarioRepository dao;
	
	@GetMapping("fundoImobiliario")
	public List<FundoImobiliario> consultar() {
		return dao.findAll();
	}

}
