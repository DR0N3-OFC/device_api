package br.edu.utfpr.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.todoapi.model.Gateway;

public interface GatewayRepository 
    extends JpaRepository<Gateway, Long> {

}