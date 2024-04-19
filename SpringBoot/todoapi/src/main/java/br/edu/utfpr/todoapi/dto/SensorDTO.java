package br.edu.utfpr.todoapi.dto;

import java.util.List;

import br.edu.utfpr.todoapi.model.Dispositivo;
import br.edu.utfpr.todoapi.model.Medicao;

public record SensorDTO(
    String nome,
    String tipo,
    Dispositivo dispositivo,
    List<Medicao> medicoes) {
}
