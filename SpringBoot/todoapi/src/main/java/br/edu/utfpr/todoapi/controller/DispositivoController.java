package br.edu.utfpr.todoapi.controller;

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

import br.edu.utfpr.todoapi.dto.DispositivoDTO;
import br.edu.utfpr.todoapi.exception.NotFoundException;
import br.edu.utfpr.todoapi.model.Dispositivo;
import br.edu.utfpr.todoapi.service.DispositivoService;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody DispositivoDTO dto) {
        try {
            var res = dispositivoService.create(dto);

            // Seta o status para 201 (CREATED) e devolve
            // o objeto dispositivo em JSON.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch(Exception ex) {
            // Seta o status para 400 (Bad request) e devolve
            // a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Obter todas as dispositivos do DB.
     */
    @GetMapping
    public List<Dispositivo> getAll() {
        return dispositivoService.getAll();
    }

    /**
     * Obter 1 dispositivo pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var gate = dispositivoService.getById(id);
        
        return gate.isPresent()
            ? ResponseEntity.ok().body(gate.get())
            : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/atuadores")
    public ResponseEntity<Object> getActuatorsByDeviceId(@PathVariable("id") long id) {
        var gate = dispositivoService.getById(id);
        
        return gate.isPresent()
            ? ResponseEntity.ok().body(gate.get().getAtuadores())
            : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/sensores")
    public ResponseEntity<Object> getSensorsByDeviceId(@PathVariable("id") long id) {
        var gate = dispositivoService.getById(id);
        
        return gate.isPresent()
            ? ResponseEntity.ok().body(gate.get().getSensores())
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id,
        @RequestBody DispositivoDTO dto) {
            try {
                return ResponseEntity.ok().body(dispositivoService.update(id, dto));
            } catch(NotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            } catch(Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        try {
            dispositivoService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
