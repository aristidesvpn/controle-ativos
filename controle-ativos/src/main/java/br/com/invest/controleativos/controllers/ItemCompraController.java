package br.com.invest.controleativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invest.controleativos.models.ItemCompra;
import br.com.invest.controleativos.repository.ItemCompraRepository;

@RestController
public class ItemCompraController {
	
	@Autowired
	private ItemCompraRepository dao;
	
	@GetMapping("itemCompra")
	public List<ItemCompra> consultar() {
		return dao.findAll();
	}

}
