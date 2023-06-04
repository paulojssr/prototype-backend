package org.ifg.prototype.controllers;

import java.util.List;
import java.util.Optional;

import org.ifg.prototype.dto.RequisitoDTO;
import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.entities.Requisito;
import org.ifg.prototype.services.RequisitoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requisitos")
public class RequisitoController {

    @Autowired
    private RequisitoService service;

    @GetMapping
    public ResponseEntity<List<RequisitoDTO>> findAll() {
        List<RequisitoDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<Object> buscarRequisito(@PathVariable(value = "codigo") Long codigo) {
        Optional<RequisitoDTO> requisitoDTOOptional = service.findById(codigo);

        if (!requisitoDTOOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requisito não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(requisitoDTOOptional.get());
        }
    }

    @PutMapping("/atualizarrequisito/{codigo}")
    public ResponseEntity<Object> requisitoUpdate(@PathVariable(value = "codigo") Long codigo,
            @RequestBody RequisitoDTO requisitoDTO) {
        requisitoDTO.setCodigo(codigo);

        Optional<RequisitoDTO> requisitoDTOOptionall = service.findById(codigo);

        if (requisitoDTOOptionall.isPresent()) {

            Requisito requisito = new Requisito();

            requisito.setCodigo(requisitoDTOOptionall.get().getCodigo());
            requisito.setProjeto(requisitoDTOOptionall.get().getProjeto());
            requisito.setIdentificacao(requisitoDTOOptionall.get().getIdentificacao());
            requisito.setNome(requisitoDTOOptionall.get().getNome());
            requisito.setAtores(requisitoDTOOptionall.get().getAtores());
            requisito.setPrioridade(requisitoDTOOptionall.get().getPrioridade());
            requisito.setDescricao(requisitoDTOOptionall.get().getDescricao());
            requisito.setAcoes(requisitoDTOOptionall.get().getAcoes());
            requisito.setValidacoes(requisitoDTOOptionall.get().getValidacoes());
            requisito.setPrompt_final(requisitoDTOOptionall.get().getPrompt_final());
            requisito.setPrototipo_final(requisitoDTOOptionall.get().getPrototipo_final());

            service.update(requisito);

            return ResponseEntity.status(HttpStatus.OK).body("Requisito Atualizado!");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Requisito não encontrado!");
        }

    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvarRequisito(@RequestBody RequisitoDTO requisitoDTO) {
        try {
            Requisito requisito = new Requisito();

            BeanUtils.copyProperties(requisitoDTO, requisito, "projeto");

            requisito.setProjeto(new Projeto());
            requisito.getProjeto().setCodigo(requisitoDTO.getCodigo());
            requisito.setCodigo(service.save(requisito).getCodigo());

            return ResponseEntity.ok().body(requisito);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}