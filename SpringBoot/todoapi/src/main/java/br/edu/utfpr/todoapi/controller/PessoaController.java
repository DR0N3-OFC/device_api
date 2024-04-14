package br.edu.utfpr.todoapi.controller;

import java.sql.SQLException;
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

import br.edu.utfpr.todoapi.dto.PessoaDTO;
import br.edu.utfpr.todoapi.exception.NotFoundException;
import br.edu.utfpr.todoapi.model.Pessoa;
import br.edu.utfpr.todoapi.service.PessoaService;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PessoaDTO dto) {
        try {
            var res = pessoaService.create(dto);

            // Seta o status para 201 (CREATED) e devolve
            // o objeto Pessoa em JSON.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            // Seta o status para 400 (Bad request) e devolve
            // a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Obter todas as pessoas do DB.
     */
    @GetMapping
    public List<Pessoa> getAll() {
        return pessoaService.getAll();
    }

    /**
     * Obter 1 pessoa pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var person = pessoaService.getById(id);
        
        return person.isPresent()
            ? ResponseEntity.ok().body(person.get())
            : ResponseEntity.notFound().build();
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id,
        @RequestBody PessoaDTO dto) {
            try {
                return ResponseEntity.ok().body(pessoaService.update(id, dto));
            } catch(NotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            } catch(Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        try {
            pessoaService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
