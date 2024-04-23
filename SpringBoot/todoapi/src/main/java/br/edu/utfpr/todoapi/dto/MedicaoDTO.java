package br.edu.utfpr.todoapi.dto;

import java.util.Date;

import br.edu.utfpr.todoapi.model.Sensor;

public record MedicaoDTO(
    Date data,
    double valor,
    Date created_at,
    Date updated_at,
    Sensor sensor) {

}
