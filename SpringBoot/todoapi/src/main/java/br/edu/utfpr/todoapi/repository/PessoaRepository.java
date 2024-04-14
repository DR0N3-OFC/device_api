package br.edu.utfpr.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.todoapi.model.Pessoa;

public interface PessoaRepository 
    extends JpaRepository<Pessoa, Long> {

}
