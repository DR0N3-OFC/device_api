package br.edu.utfpr.deviceapi.dto;

import java.util.Date;
import java.util.List;

import br.edu.utfpr.deviceapi.model.Atuador;
import br.edu.utfpr.deviceapi.model.Gateway;
import br.edu.utfpr.deviceapi.model.Sensor;

public record DispositivoDTO(
    String nome,
    String descricao,
    String localizacao,
    String endereco,
    Gateway gateway,
    Date created_at,
    Date updated_at,
    List<Atuador> atuadores,
    List<Sensor> sensores) {

}
