package br.edu.utfpr.todoapi.dto;

import java.util.Date;

import br.edu.utfpr.todoapi.model.Dispositivo;

public record AtuadorDTO(
    String nome,
    Date created_at,
    Date updated_at,
    Dispositivo dispositivo) {

}
