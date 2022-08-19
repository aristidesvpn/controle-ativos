package br.com.invest.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invest.controleativos.models.NotaCorretagem;


public interface NotaCorretagemRepository extends JpaRepository<NotaCorretagem, Long> {
	
	

}
