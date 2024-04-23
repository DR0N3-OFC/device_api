package br.edu.utfpr.todoapi.dto;

import java.util.Date;
import java.util.List;

import br.edu.utfpr.todoapi.model.Dispositivo;
import br.edu.utfpr.todoapi.model.Medicao;

public record SensorDTO(
    String nome,
    String tipo,
    Dispositivo dispositivo,
    Date created_at,
    Date updated_at,
    List<Medicao> medicoes) {
}
