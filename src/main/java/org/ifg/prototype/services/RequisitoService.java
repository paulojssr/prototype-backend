package org.ifg.prototype.services;

import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.ifg.prototype.entities.Requisito;
import org.ifg.prototype.repositories.RequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ifg.prototype.dto.RequisitoDTO;

@Service
public class RequisitoService {

    @Autowired
    private final RequisitoRepository requisitoRepository;

    public RequisitoService(RequisitoRepository requisitoRepository) {
        this.requisitoRepository = requisitoRepository;
    }

    @Transactional(readOnly = true)
    public List<RequisitoDTO> findAll() {
        List<Requisito> result = requisitoRepository.findAll();

        return result.stream().map(RequisitoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Optional<RequisitoDTO> findById(Long id) {
        Optional<Requisito> requisitoOptional = requisitoRepository.findById(id);

        return requisitoOptional.map(RequisitoDTO::new);
    }

    @Transactional
    public Requisito save(Requisito requisito) {
        return requisitoRepository.save(requisito);
    }

    @Transactional
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
