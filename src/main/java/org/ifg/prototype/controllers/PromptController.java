package org.ifg.prototype.controllers;

import org.ifg.prototype.dto.PromptDTO;
import org.ifg.prototype.entities.Prompt;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.services.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * Um controlador para a entidade {@link Prompt}.
 * Crated by Daniel 04/06/2023.
 */
@RestController
@RequestMapping(value = "/prompts", produces = "application/json")
public class PromptController {

    @Autowired
    private PromptService promptService;

    private final String regexValidaNomePrompt = "^[a-zA-Z0-9À-ÿ ]{10,60}$";
    private final String regexValidaPromptModelo = "^[a-zA-Z0-9À-ÿ .,:-]{10,5000}$";

    /**
     * Método que salva um prompt
     * @param promptDTO
     * @return ResponseEntity<Object>
     */
    @PostMapping(value = "/salvarprompt")
    public ResponseEntity<Object> salvarPrompt(@RequestBody PromptDTO promptDTO) {

        // Validações de campos obrigatórios e regex de validação de campos de entrada nome e prompt modelo
        var matchNomePrompt = promptDTO.getNome().matches(regexValidaNomePrompt);
        if (!matchNomePrompt) {
            return ResponseEntity.badRequest().body("O nome deve ter entre 10 e 60 caracteres" +
                    " e não pode conter caracteres especiais");
        }

        var matchPromptModelo = promptDTO.getPromptModelo().matches(regexValidaPromptModelo);
        if (!matchPromptModelo) {
            return ResponseEntity.badRequest().body("O prompt modelo deve ter entre 10 e 5000" +
                    " caracteres e não pode conter caracteres especiais");
        }

        try {
            Prompt prompt = new Prompt();
            prompt.setNome(promptDTO.getNome());
            prompt.setUsuario(new Usuario());
            prompt.getUsuario().setCodigo(promptDTO.getCodigoUsuario());
            prompt.setDataCriacao(LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC));
            prompt.setPromptModelo(promptDTO.getPromptModelo());
            prompt.setCodigo(promptService.save(prompt).getCodigo());
            return ResponseEntity.ok().body(prompt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao salvar prompt");
        }
    }

    /**
     * Método que atualiza um prompt
     * @param codigo
     * @param promptDTO
     * @return ResponseEntity<Object>
     */
    @PutMapping("/atualizarprompt/{codigo}")
    public ResponseEntity<Object> atualizarPrompt(@PathVariable("codigo") Long codigo,
                                                  @RequestBody PromptDTO promptDTO) {

        var matchNomePrompt = promptDTO.getNome().matches(regexValidaNomePrompt);
        if (!matchNomePrompt) {
            return ResponseEntity.badRequest().body("O nome deve ter entre 10 e 60 caracteres" +
                    " e não pode conter caracteres especiais");
        }

        var matchPromptModelo = promptDTO.getPromptModelo().matches(regexValidaPromptModelo);
        if (!matchPromptModelo) {
            return ResponseEntity.badRequest().body("O prompt modelo deve ter entre 10 e 5000" +
                    " caracteres e não pode conter caracteres especiais");
        }

        promptDTO.setCodigo(codigo);
        Optional<PromptDTO> promptOptional = promptService.findById(codigo);
        if (promptOptional.isPresent()) {
            Prompt prompt = new Prompt();
            prompt.setCodigo(codigo);
            prompt.setNome(promptDTO.getNome());
            prompt.setDataCriacao(promptOptional.get().getDataCriacao());
            prompt.setPromptModelo(promptDTO.getPromptModelo());
            prompt.setUsuario(promptOptional.get().getUsuario());
            promptService.update(prompt);
            return ResponseEntity.status(HttpStatus.OK).body("Prompt Atualizado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prompt Não Encontrado!");
        }
    }

    /**
     * Método que pesquisa um prompt
     * @param codigo
     * @return ResponseEntity<Object>
     */
    @GetMapping("/pesquisarprompt/{codigo}")
    public ResponseEntity<Object> pesquisarPrompt(@PathVariable("codigo") Long codigo) {
        Optional<PromptDTO> promptOptional = promptService.findById(codigo);
        if (promptOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(promptOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prompt Não Encontrado!");
        }
    }

/**
     * Método que deleta um prompt
     * @param codigo
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/deletarprompt/{codigo}")
    public ResponseEntity<Object> deletarPrompt(@PathVariable("codigo") Long codigo) {
        Optional<PromptDTO> promptOptional = promptService.findById(codigo);
        if (promptOptional.isPresent()) {
            promptService.delete(codigo);
            return ResponseEntity.status(HttpStatus.OK).body("Prompt Deletado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prompt Não Encontrado!");
        }
    }


    /**
     * Método que pesquisa todos os prompts
     * @return ResponseEntity<Object>
     */
    @GetMapping
    public ResponseEntity<Object> pesquisarPrompts() {
        return ResponseEntity.status(HttpStatus.OK).body(promptService.findAll());
    }
}