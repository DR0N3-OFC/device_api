package br.edu.utfpr.todoapi.dto;

import java.util.Date;

public record MedicaoDTO(
    Date data,
    double valor) {

}
