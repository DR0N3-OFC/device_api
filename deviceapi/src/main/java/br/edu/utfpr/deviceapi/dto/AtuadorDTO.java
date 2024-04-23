package br.edu.utfpr.deviceapi.dto;

import java.util.Date;

import br.edu.utfpr.deviceapi.model.Dispositivo;

public record AtuadorDTO(
    String nome,
    Date created_at,
    Date updated_at,
    Dispositivo dispositivo) {

}
