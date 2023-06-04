package org.ifg.prototype.dto;

import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.entities.Requisito;

public class RequisitoDTO {

    private Long codigo;
    private Projeto projeto;
    private String identificacao;
    private String nome;
    private String atores;
    private String prioridade;
    private String descricao;
    private String acoes;
    private String validacoes;
    private String prompt_final;
    private String prototipo_final;
    private Long codigo_projeto;

    public RequisitoDTO() {

    }

    public RequisitoDTO(Requisito requisito) {
        this.codigo = requisito.getCodigo();
        this.projeto = requisito.getProjeto();
        this.identificacao = requisito.getIdentificacao();
        this.nome = requisito.getNome();
        this.atores = requisito.getAtores();
        this.prioridade = requisito.getPrioridade();
        this.descricao = requisito.getDescricao();
        this.acoes = requisito.getAcoes();
        this.validacoes = requisito.getValidacoes();
        this.prompt_final = requisito.getPrompt_final();
        this.prototipo_final = requisito.getPrototipo_final();
    }

    public RequisitoDTO(Requisito requisito, Long codigo_projeto) {
        this.codigo = requisito.getCodigo();
        this.projeto = requisito.getProjeto();
        this.identificacao = requisito.getIdentificacao();
        this.nome = requisito.getNome();
        this.atores = requisito.getAtores();
        this.prioridade = requisito.getPrioridade();
        this.descricao = requisito.getDescricao();
        this.acoes = requisito.getAcoes();
        this.validacoes = requisito.getValidacoes();
        this.prompt_final = requisito.getPrompt_final();
        this.prototipo_final = requisito.getPrototipo_final();
        this.codigo_projeto = codigo_projeto;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAcoes() {
        return acoes;
    }

    public void setAcoes(String acoes) {
        this.acoes = acoes;
    }

    public String getValidacoes() {
        return validacoes;
    }

    public void setValidacoes(String validacoes) {
        this.validacoes = validacoes;
    }

    public String getPrompt_final() {
        return prompt_final;
    }

    public void setPrompt_final(String prompt_final) {
        this.prompt_final = prompt_final;
    }

    public String getPrototipo_final() {
        return prototipo_final;
    }

    public void setPrototipo_final(String prototipo_final) {
        this.prototipo_final = prototipo_final;
    }

    public Long getCodigo_projeto() {
        return codigo_projeto;
    }

    public void setCodigo_projeto(Long codigo_projeto) {
        this.codigo_projeto = codigo_projeto;
    }

}
