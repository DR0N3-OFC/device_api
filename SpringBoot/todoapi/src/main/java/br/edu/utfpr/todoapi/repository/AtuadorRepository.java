package br.edu.utfpr.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.todoapi.model.Atuador;

public interface AtuadorRepository extends JpaRepository<Atuador, Long> {

}
