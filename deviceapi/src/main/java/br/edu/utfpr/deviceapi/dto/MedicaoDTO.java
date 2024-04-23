package br.edu.utfpr.deviceapi.dto;

import java.util.Date;

import br.edu.utfpr.deviceapi.model.Sensor;

public record MedicaoDTO(
    Date data,
    double valor,
    Date created_at,
    Date updated_at,
    Sensor sensor) {

}
