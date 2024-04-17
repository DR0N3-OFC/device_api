package br.edu.utfpr.todoapi.dto;

import br.edu.utfpr.todoapi.model.Dispositivo;

public record AtuadorDTO(
    String nome,
    Dispositivo dispositivo) {

}
