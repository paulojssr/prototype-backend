package org.ifg.prototype.services;

import org.ifg.prototype.dto.PromptDTO;
import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.entities.Prompt;
import org.ifg.prototype.repositories.PromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Uma classe de serviço para a entidade {@link Prompt}.
 * Criado por Daniel 04/06/2023.
 */
@Service
public class PromptService {

    /**
     * Injeção de dependência
     */
    @Autowired
    PromptRepository promptRepository;

    /**
     * Construtor padrão
     */
    public PromptService() {
    }

    /**
     * Salva uma entidade tipo {@link Prompt}.
     *
     * @param prompt
     */
    public Prompt save(Prompt prompt) {
        return promptRepository.save(prompt);
    }

    /**
     * Altera uma entidade tipo {@link Prompt}.
     *
     * @param prompt
     */
    public Prompt update(Prompt prompt) {
        return promptRepository.save(prompt);
    }

    /**
     * Procura uma entidade por id tipo {@link Prompt}.
     *
     * @param id
     */
    public void delete(Long id) {
        promptRepository.deleteById(id);
    }

    /**
     * Procura uma entidade por id tipo {@link Prompt}.
     *
     * @param id
     * @return Optional<Prompt>
     */
    public Optional<PromptDTO> findById(Long id) {
        Optional<Prompt> promptOptional = promptRepository.findById(id);
        return promptOptional.map(PromptDTO::new);
    }

    /**
     * lista todas as entidades prompt {@link Prompt}.
     *
     * @return Optional<Prompt>
     */
    @Transactional (readOnly = true)
    public List<PromptDTO> findAll() {
        return promptRepository.findAll().stream()
                .map(PromptDTO::new).collect(Collectors.toList());
    }
}