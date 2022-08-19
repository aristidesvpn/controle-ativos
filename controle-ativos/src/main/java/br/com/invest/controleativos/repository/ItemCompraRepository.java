package br.com.invest.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invest.controleativos.models.ItemCompra;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {

}
