package br.edu.utfpr.todoapi.dto;

import java.util.Date;
import java.util.List;

import br.edu.utfpr.todoapi.model.Dispositivo;

public record GatewayDTO(
    String nome, 
    String descricao, 
    String endereco,
    Date created_at,
    Date updated_at,
    List<Dispositivo> dispositivos) {

}
