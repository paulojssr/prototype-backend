package org.ifg.prototype.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ifg.prototype.dto.DadosRequisitoDTO;
import org.ifg.prototype.entities.DadosRequisito;
import org.ifg.prototype.repositories.DadosRequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DadosRequisitoService {

    @Autowired
    private final DadosRequisitoRepository dadosRequistoRepository;

    public DadosRequisitoService(DadosRequisitoRepository dadosRequisitoRepository) {
        this.dadosRequistoRepository = dadosRequisitoRepository;
    }

    @Transactional(readOnly = true)
    public List<DadosRequisitoDTO> findAll() {
        List<DadosRequisito> result = dadosRequistoRepository.findAll();

        return result.stream().map(DadosRequisitoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Optional<DadosRequisitoDTO> findById(Long id) {
        Optional<DadosRequisito> requisitoOptional = dadosRequistoRepository.findById(id);

        return requisitoOptional.map(DadosRequisitoDTO::new);
    }

    @Transactional
    public DadosRequisito save(DadosRequisito requisito) {
        return dadosRequistoRepository.save(requisito);
    }

    @Transactional
    public void update(DadosRequisito requisito) {
        dadosRequistoRepository.save(requisito);
    }

    public void deleteRequisito(Long id) {
        dadosRequistoRepository.deleteById(id);
    }

    public void exist(Long id) {
        dadosRequistoRepository.existsById(id);
    }

}
