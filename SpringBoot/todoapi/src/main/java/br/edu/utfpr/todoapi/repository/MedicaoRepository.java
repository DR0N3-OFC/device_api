package br.edu.utfpr.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.todoapi.model.Medicao;

public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

}
