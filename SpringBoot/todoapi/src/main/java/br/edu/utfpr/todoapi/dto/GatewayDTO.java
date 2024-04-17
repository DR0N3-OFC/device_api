package br.edu.utfpr.todoapi.dto;

import java.util.List;

import br.edu.utfpr.todoapi.model.Dispositivo;

public record GatewayDTO(
    String nome, 
    String descricao, 
    String endereco,
    List<Dispositivo> dispositivos) {

}
