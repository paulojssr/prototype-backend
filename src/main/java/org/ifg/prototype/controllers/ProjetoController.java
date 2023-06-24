package org.ifg.prototype.controllers;

import org.ifg.prototype.dto.ProjetoDTO;
import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.entities.enums.ProjetoStatus;
import org.ifg.prototype.services.ProjetoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

/**
 * Criado por Daniel 03/06/2023.
 */
@RestController
@RequestMapping(value = "/projetos")
public class ProjetoController {

    /**
     * Injeção de dependência
     */
    @Autowired
    private ProjetoService projetoService;

    /**
     * Método salvar projeto no banco de dados e retornar o objeto salvo
     * 
     * @return
     */
    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvar(@RequestBody ProjetoDTO projetoDTO) {
        try {
            Projeto projeto = new Projeto();
            BeanUtils.copyProperties(projetoDTO, projeto, "usuario");
            projeto.setUsuario(new Usuario());
            projeto.getUsuario().setCodigo(projetoDTO.getCodigoUsuario());
            projeto.setDataInicio(LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC));
            projeto.setStatus(ProjetoStatus.ATIVO);
            projeto.setCodigo(projetoService.save(projeto).getCodigo());
            return ResponseEntity.ok().body(projeto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Método para atualizar o projeto no banco de dados
     * 
     * @param codigo
     * @param projetoDTO
     * @return
     */
    @PutMapping("/atualizarprojeto/{codigo}")
    public ResponseEntity<Object> projetoUpdate(@PathVariable(value = "codigo") Long codigo,
            @RequestBody ProjetoDTO projetoDTO) {
        projetoDTO.setCodigo(codigo);
        Optional<ProjetoDTO> projetoOptional = projetoService.findById(codigo);
        if (projetoOptional.isPresent()) {
            Projeto projeto = new Projeto();
            // BeanUtils.copyProperties(projetoDTO, projeto);

            projeto.setCodigo(projetoOptional.get().getCodigo());
            projeto.setNome(projetoDTO.getNome());
            projeto.setDescricao(projetoDTO.getDescricao());
            projeto.setDataInicio(projetoOptional.get().getDataInicio());
            projeto.setDataFim(projetoOptional.get().getDataFim());
            projeto.setUsuario(projetoOptional.get().getUsuario());
            projeto.setStatus(projetoOptional.get().getStatus());

            projetoService.update(projeto);
            return ResponseEntity.status(HttpStatus.OK).body("Projeto Atualizado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto Não Encontrado!");
        }
    }

    /**
     * Método para deletar o projeto no banco de dados
     * 
     * @param codigo
     * @return
     */
    @GetMapping("/pesquisarprojeto/{codigo}")
    public ResponseEntity<Object> buscarProjeto(@PathVariable(value = "codigo") Long codigo) {
        Optional<ProjetoDTO> projetoDTOOptional = projetoService.findById(codigo);
        if (!projetoDTOOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(projetoDTOOptional.get());
        }
    }

    /**
     * Método para listar todos os projetos no banco de dados
     * 
     * @param
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> findAll() {
        List<ProjetoDTO> list = projetoService.findAll();
        return ResponseEntity.ok(list);
    }
}