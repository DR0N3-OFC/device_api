package br.edu.utfpr.deviceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.deviceapi.dto.GatewayDTO;
import br.edu.utfpr.deviceapi.exception.NotFoundException;
import br.edu.utfpr.deviceapi.model.Dispositivo;
import br.edu.utfpr.deviceapi.model.Gateway;
import br.edu.utfpr.deviceapi.service.GatewayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gateway")
@Tag(name = "Gateway", description = "Endpoint para operações relacionadas a gateways")
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;

    @PostMapping
    @Operation(summary = "Criar um novo gateway", description = "Registra um novo objeto de gateway com base no DTO recebido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sucesso, retorna o gateway", content = @Content(schema = @Schema(implementation = Gateway.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum gateway com o ID fornecido")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody GatewayDTO dto) {
        try {
            var res = gatewayService.create(dto);

            // Seta o status para 201 (CREATED) e devolve
            // o objeto gateway em JSON.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            // Seta o status para 400 (Bad request) e devolve
            // a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Obter todas as gateways do DB.
     */
    @GetMapping
    @Operation(summary = "Obter todos os gateways")
    public List<Gateway> getAll() {
        return gatewayService.getAll();
    }

    /**
     * Obter 1 gateway pelo ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter um gateway pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, retorna o gateway", content = @Content(schema = @Schema(implementation = Gateway.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum gateway com o ID fornecido")
    })
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var gateway = gatewayService.getById(id);
        
        return gateway.isPresent()
            ? ResponseEntity.ok().body(gateway.get())
            : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/dispositivos")
    @Operation(summary = "Obter dispositivos por ID do gateway")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, retorna os dispositivos do gateway", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Dispositivo.class)))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum sensor com o ID fornecido")
    })
    public ResponseEntity<Object> getDevicesByGatewayId(@PathVariable("id") long id) {
        var gateway = gatewayService.getById(id);
        
        return gateway.isPresent()
            ? ResponseEntity.ok().body(gateway.get().getDispositivos())
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um gateway com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, retorna o gateway atualizado", content = @Content(schema = @Schema(implementation = Gateway.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum gateway com o ID fornecido"),
        @ApiResponse(responseCode = "400", description = "ERRO, ocorreu algum erro na requisição")
    })
    public ResponseEntity<Object> update(@PathVariable long id,
        @RequestBody GatewayDTO dto) {
            try {
                return ResponseEntity.ok().body(gatewayService.update(id, dto));
            } catch(NotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            } catch(Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um gateway com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, gateway deletado"),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum gateway com o ID fornecido"),
        @ApiResponse(responseCode = "400", description = "ERRO, ocorreu algum erro na requisição")
    })
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        try {
            gatewayService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
