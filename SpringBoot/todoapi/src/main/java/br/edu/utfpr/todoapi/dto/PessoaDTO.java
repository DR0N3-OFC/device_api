package br.edu.utfpr.todoapi.dto;

import java.util.List;

import br.edu.utfpr.todoapi.model.Gateway;

public record PessoaDTO(
    String nome,
    String email,
    String senha,
    List<Gateway> gateways) {
}
