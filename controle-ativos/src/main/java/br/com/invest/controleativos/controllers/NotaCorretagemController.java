package br.com.invest.controleativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invest.controleativos.models.NotaCorretagem;
import br.com.invest.controleativos.repository.NotaCorretagemRepository;

@RestController
public class NotaCorretagemController {
	
	@Autowired
	private NotaCorretagemRepository dao;
	
	@GetMapping("notaCorretagem")
	public List<NotaCorretagem> consultar() {
		return dao.findAll();
	}

}
