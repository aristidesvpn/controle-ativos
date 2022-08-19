package br.com.invest.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invest.controleativos.models.Corretora;

public interface CorretoraRepository extends JpaRepository<Corretora, Long> {

}
