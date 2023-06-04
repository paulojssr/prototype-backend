package org.ifg.prototype.services;

import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

import org.ifg.prototype.entities.Requisito;
import org.ifg.prototype.repositories.RequisitoRepository;
import org.ifg.prototype.dto.RequisitoDTO;

public class RequisitoService {

    private final RequisitoRepository requisitoRepository;

    public RequisitoService(RequisitoRepository requisitoRepository) {
        this.requisitoRepository = requisitoRepository;
    }

    public List<RequisitoDTO> findAll() {
        List<Requisito> result = requisitoRepository.findAll();

        return result.stream().map(RequisitoDTO::new).collect(Collectors.toList());
    }

    public Optional<RequisitoDTO> findById(Long id) {
        Optional<Requisito> requisitoOptional = requisitoRepository.findById(id);

        return requisitoOptional.map(RequisitoDTO::new);
    }

    public Requisito save(Requisito requisito) {
        return requisitoRepository.save(requisito);
    }

    public void update(Requisito requisito) {
        requisitoRepository.save(requisito);
    }

    public void deleteRequisito(Long id) {
        requisitoRepository.deleteById(id);
    }

    public void exist(Long id) {
        requisitoRepository.existsById(id);
    }
}
