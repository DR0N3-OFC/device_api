package br.edu.utfpr.todoapi.dto;

import java.util.List;

import br.edu.utfpr.todoapi.model.Atuador;
import br.edu.utfpr.todoapi.model.Gateway;
import br.edu.utfpr.todoapi.model.Sensor;

public record DispositivoDTO(
    String nome,
    String descricao,
    String localizacao,
    String endereco,
    Gateway gateway,
    List<Atuador> atuadores,
    List<Sensor> sensores) {

}
