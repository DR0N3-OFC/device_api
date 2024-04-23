package br.edu.utfpr.deviceapi.dto;

import java.util.Date;
import java.util.List;

import br.edu.utfpr.deviceapi.model.Dispositivo;
import br.edu.utfpr.deviceapi.model.Pessoa;

public record GatewayDTO(
    String nome, 
    String descricao, 
    String endereco,
    Date created_at,
    Date updated_at,
    Pessoa pessoa,
    List<Dispositivo> dispositivos) {

}
