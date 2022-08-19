package br.com.invest.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invest.controleativos.models.FundoImobiliario;

public interface FundoImobiliarioRepository extends JpaRepository<FundoImobiliario, Long> {

}
