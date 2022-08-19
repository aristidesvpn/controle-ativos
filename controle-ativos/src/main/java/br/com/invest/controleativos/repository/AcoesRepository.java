package br.com.invest.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invest.controleativos.models.Acoes;

public interface AcoesRepository extends JpaRepository<Acoes, Long> {

}
