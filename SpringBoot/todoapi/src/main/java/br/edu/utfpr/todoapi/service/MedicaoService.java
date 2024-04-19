package br.edu.utfpr.todoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.todoapi.dto.MedicaoDTO;
import br.edu.utfpr.todoapi.exception.NotFoundException;
import br.edu.utfpr.todoapi.model.Medicao;
import br.edu.utfpr.todoapi.repository.MedicaoRepository;



@Service
public class MedicaoService {
    @Autowired
    private MedicaoRepository medicaoRepository;


    /**
     * Inserir uma medicao no DB.
     * @return
     */
    public Medicao create(MedicaoDTO dto){
        var medicao = new Medicao();
        BeanUtils.copyProperties(dto, medicao);

        // Persistir no Banco de dados
        return medicaoRepository.save(medicao);
    }


    /**
     * Buscar no banco de dados todas.
     * @return
     */
    public List<Medicao> getAll() {
        return medicaoRepository.findAll();
    }

    /**
     * Buscar um medicao pelo ID.
     * @param id
     * @return
     */
    public Optional<Medicao> getById(long id) {
        return medicaoRepository.findById(id);
    }

    public Medicao update(long id, MedicaoDTO dto) throws NotFoundException {
        var res = medicaoRepository.findById(id);

        if(res.isEmpty()) {
            throw new NotFoundException("Medicao " + id + " não existe.");
        }

        var medicao = res.get();
        medicao.setValor(dto.valor());
        medicao.setData(dto.data());
        medicao.setSensor(dto.sensor());

        return medicaoRepository.save(medicao);
    }
    public void delete(long id) throws NotFoundException {
        var res = medicaoRepository.findById(id);

        if(res.isEmpty()) {
            throw new NotFoundException("Medicao " + id + " não existe.");
        }

        medicaoRepository.delete(res.get());
    }
}
