package br.edu.utfpr.deviceapi.dto;

import java.util.List;

import br.edu.utfpr.deviceapi.model.Gateway;

public record PessoaDTO(
    String nome,
    String email,
    String senha,
    List<Gateway> gateways) {
}
