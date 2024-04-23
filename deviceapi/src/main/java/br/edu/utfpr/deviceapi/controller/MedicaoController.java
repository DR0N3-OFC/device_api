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

import br.edu.utfpr.deviceapi.dto.MedicaoDTO;
import br.edu.utfpr.deviceapi.exception.NotFoundException;
import br.edu.utfpr.deviceapi.model.Medicao;
import br.edu.utfpr.deviceapi.service.MedicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/medicao")
@Tag(name = "Medicao", description = "Endpoint para operações relacionadas a medicoes")
public class MedicaoController {
    @Autowired
    private MedicaoService medicaoService;

    @PostMapping
    @Operation(summary = "Criar um novo medicao", description = "Registra um novo objeto de medicao com base no DTO recebido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sucesso, retorna o medicao", content = @Content(schema = @Schema(implementation = Medicao.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum medicao com o ID fornecido")
    })
    public ResponseEntity<Object> create(@RequestBody MedicaoDTO dto) {
        try {
            var res = medicaoService.create(dto);

            // Seta o status para 201 (CREATED) e devolve
            // o objeto medicao em JSON.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            // Seta o status para 400 (Bad request) e devolve
            // a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Obter todas as medicaos do DB.
     */
    @GetMapping
    @Operation(summary = "Obter todos os medicoes")
    public List<Medicao> getAll() {
        return medicaoService.getAll();
    }

    /**
     * Obter 1 medicao pelo ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter um medicao pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, retorna o medicao", content = @Content(schema = @Schema(implementation = Medicao.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum medicao com o ID fornecido")
    })
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var medicao = medicaoService.getById(id);
        
        return medicao.isPresent()
            ? ResponseEntity.ok().body(medicao.get())
            : ResponseEntity.notFound().build();
    }
    

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um medicao com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, retorna o medicao atualizado", content = @Content(schema = @Schema(implementation = Medicao.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum medicao com o ID fornecido"),
        @ApiResponse(responseCode = "400", description = "ERRO, ocorreu algum erro na requisição")
    })
    public ResponseEntity<Object> update(@PathVariable long id,
        @RequestBody MedicaoDTO dto) {
            try {
                return ResponseEntity.ok().body(medicaoService.update(id, dto));
            } catch(NotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            } catch(Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um medicao com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso, medicao deletado"),
        @ApiResponse(responseCode = "404", description = "Não encontrado, nenhum medicao com o ID fornecido"),
        @ApiResponse(responseCode = "400", description = "ERRO, ocorreu algum erro na requisição")
    })
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        try {
            medicaoService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
