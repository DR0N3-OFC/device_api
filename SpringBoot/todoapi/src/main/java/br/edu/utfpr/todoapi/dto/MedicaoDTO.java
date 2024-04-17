package br.edu.utfpr.todoapi.dto;

import java.util.Date;

import br.edu.utfpr.todoapi.model.Sensor;

public record MedicaoDTO(
    Date data,
    double valor,
    Sensor sensor) {

}
