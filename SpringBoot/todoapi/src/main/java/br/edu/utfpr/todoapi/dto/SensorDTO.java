package br.edu.utfpr.todoapi.dto;

import java.util.List;

import br.edu.utfpr.todoapi.model.Medicao;

public record SensorDTO(
    String nome,
    String tipo,
    DispositivoDTO dispositivo,
    List<Medicao> medicoes) {
}
