package br.edu.utfpr.deviceapi.dto;

import java.util.Date;
import java.util.List;

import br.edu.utfpr.deviceapi.model.Dispositivo;
import br.edu.utfpr.deviceapi.model.Medicao;

public record SensorDTO(
    String nome,
    String tipo,
    Dispositivo dispositivo,
    Date created_at,
    Date updated_at,
    List<Medicao> medicoes) {
}
