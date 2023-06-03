package org.ifg.prototype.services;

import org.ifg.prototype.dto.ProjetoDTO;
import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.repositories.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Uma classe de serviço para a entidade {@link Projeto}.
 * Criado por Daniel 03/06/2023.
 */
@Service
public class ProjetoService {

    /**
     * A instância do repositório para a entidade {@link Projeto}.
     * Injeção de dependência da classe ProjetoRepository
     * para a classe ProjetoService através do construtor.
     */
    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    /**
     * Salva uma entidade tipo {@link Projeto}.
     *
     * @param projeto
     */
    @Transactional
    public Projeto save(Projeto projeto) {
       return projetoRepository.save(projeto);
    }

    /**
     * Altera uma entidade tipo {@link Projeto}.
     *
     * @param projeto
     */
    @Transactional
    public void update(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    /**
     * Procura uma entidade por id tipo {@link Projeto}.
     *
     * @param id
     * @return
     */
    @Transactional
    public Optional<ProjetoDTO> findById(Long id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);
        return projetoOptional.map(ProjetoDTO::new);
    }

    /**
     * Find all {@link Projeto} entities.
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProjetoDTO> findAll() {
        List<Projeto> result = projetoRepository.findAll();
        return result.stream().map(ProjetoDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Delete a {@link Projeto} entity by id.
     *
     * @param id
     */
    public void deleteById(Long id) {
        projetoRepository.deleteById(id);
    }

    /**
     * Count all {@link Projeto} entities.
     */
    public void count() {
        projetoRepository.count();
    }

    /**
     * Check if a {@link Projeto} entity exists by id.
     *
     * @param id
     */
    public void existsById(Long id) {
        projetoRepository.existsById(id);
    }
}