package org.ifg.prototype.controllers;

import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Pointcut;
import org.ifg.prototype.dto.DadosRequisitoDTO;
import org.ifg.prototype.dto.RequisitoDTO;
import org.ifg.prototype.entities.DadosRequisito;
import org.ifg.prototype.entities.Requisito;
import org.ifg.prototype.services.DadosRequisitoService;
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
@RequestMapping(value = "/dadosRequisito")
public class DadosRequisitoController {

    @Autowired
    private DadosRequisitoService service;

    @GetMapping
    public ResponseEntity<List<DadosRequisitoDTO>> findAll() {
        List<DadosRequisitoDTO> list = service.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<Object> buscarDadoDoRequisito(@PathVariable(value = "codigo") Long codigo) {
        Optional<DadosRequisitoDTO> dadoRequisitoDTOOptional = service.findById(codigo);

        if (!dadoRequisitoDTOOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados do Requisito Não Encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(dadoRequisitoDTOOptional.get());
        }
    }

    @PutMapping("/atualizarDadosDoRequisito")
    public ResponseEntity<Object> dadosRequisitoUpdate(@PathVariable(value = "codigo") Long codigo,
            @RequestBody DadosRequisitoDTO dadosRequisitoDTO) {

        dadosRequisitoDTO.getCodigo();

        Optional<DadosRequisitoDTO> dadosRequisitoDTOOptional = service.findById(codigo);

        if (dadosRequisitoDTOOptional.isPresent()) {

            DadosRequisito dadosRequisito = new DadosRequisito();

            dadosRequisito.setCodigo(dadosRequisitoDTOOptional.get().getCodigo());
            dadosRequisito.setDescricao(dadosRequisitoDTOOptional.get().getDescricao());
            dadosRequisito.setObrigatorio(dadosRequisitoDTOOptional.get().getObrigatorio());
            dadosRequisito.setTipo(dadosRequisitoDTOOptional.get().getTipo());
            dadosRequisito.setMax_caracteres(dadosRequisitoDTOOptional.get().getMax_caracteres());
            dadosRequisito.setRequisito(dadosRequisitoDTOOptional.get().getRequisito());

            service.update(dadosRequisito);

            return ResponseEntity.status(HttpStatus.OK).body("Dados do Requisito Atualizados!");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados do Requisito Não Encontrado");
        }

    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvarDadosDosRequisitos(@RequestBody DadosRequisitoDTO dadosRequisitoDTO) {
        try {
            DadosRequisito dadosRequisito = new DadosRequisito();

            BeanUtils.copyProperties(dadosRequisitoDTO, dadosRequisito, "requisito");

            dadosRequisito.setRequisito(new Requisito());
            dadosRequisito.getRequisito().setCodigo(dadosRequisitoDTO.getCodigo());
            dadosRequisito.setCodigo(service.save(dadosRequisito).getCodigo());

            return ResponseEntity.ok().body(dadosRequisito);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
