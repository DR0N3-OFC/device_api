package br.edu.utfpr.todoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.todoapi.dto.DispositivoDTO;
import br.edu.utfpr.todoapi.exception.NotFoundException;
import br.edu.utfpr.todoapi.model.Dispositivo;
import br.edu.utfpr.todoapi.repository.DispositivoRepository;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;


    /**
     * Inserir uma dispositivo no DB.
     * @return
     */
    public Dispositivo create(DispositivoDTO dto){
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dto, dispositivo);

        // Persistir no Banco de dados
        return dispositivoRepository.save(dispositivo);
    }


    /**
     * Buscar no banco de dados todas.
     * @return
     */
    public List<Dispositivo> getAll() {
        return dispositivoRepository.findAll();
    }

    /**
     * Buscar um dispositivo pelo ID.
     * @param id
     * @return
     */
    public Optional<Dispositivo> getById(long id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo update(long id, DispositivoDTO dto) throws NotFoundException {
        var res = dispositivoRepository.findById(id);

        if(res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não existe.");
        }

        var dispositivo = res.get();
        dispositivo.setNome(dto.nome());
        dispositivo.setDescricao(dto.descricao());
        dispositivo.setLocalizacao(dto.localizacao());
        dispositivo.setEndereco(dto.endereco());

        return dispositivoRepository.save(dispositivo);
    }
    public void delete(long id) throws NotFoundException {
        var res = dispositivoRepository.findById(id);

        if(res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não existe.");
        }

        dispositivoRepository.delete(res.get());
    }
}
