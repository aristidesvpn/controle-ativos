package br.com.invest.controleativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invest.controleativos.models.Acoes;
import br.com.invest.controleativos.repository.AcoesRepository;

@RestController
public class AcoesController {
	
	@Autowired
	private AcoesRepository dao;
	
	@GetMapping("acoes")
	public List<Acoes> consultar() {
		return dao.findAll();
	}

}
