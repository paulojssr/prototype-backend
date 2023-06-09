package org.ifg.prototype.dto;

import org.ifg.prototype.entities.DadosRequisito;
import org.ifg.prototype.entities.Requisito;

public class DadosRequisitoDTO {

    private Long codigo;
    private Requisito requisito;
    private String descricao;
    private String obrigatorio;
    private String tipo;
    private int max_caracteres;
    private Long requisito_codigo;

    public DadosRequisitoDTO() {

    }

    public DadosRequisitoDTO(DadosRequisito dadosRequisito) {

        this.codigo = dadosRequisito.getCodigo();
        this.requisito = dadosRequisito.getRequisito();
        this.descricao = dadosRequisito.getDescricao();
        this.obrigatorio = dadosRequisito.getObrigatorio();
        this.tipo = dadosRequisito.getTipo();
        this.max_caracteres = dadosRequisito.getMax_caracteres();

    }

    public DadosRequisitoDTO(DadosRequisito dadosRequisito, Long requisito_codigo) {

        this.codigo = dadosRequisito.getCodigo();
        this.requisito = dadosRequisito.getRequisito();
        this.descricao = dadosRequisito.getDescricao();
        this.obrigatorio = dadosRequisito.getObrigatorio();
        this.tipo = dadosRequisito.getTipo();
        this.max_caracteres = dadosRequisito.getMax_caracteres();
        this.requisito_codigo = requisito_codigo;

    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(String obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMax_caracteres() {
        return max_caracteres;
    }

    public void setMax_caracteres(int max_caracteres) {
        this.max_caracteres = max_caracteres;
    }

    public Long getRequisito_codigo() {
        return requisito_codigo;
    }

    public void setRequisito_codigo(Long requisito_codigo) {
        this.requisito_codigo = requisito_codigo;
    }

}