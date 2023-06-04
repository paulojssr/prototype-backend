package org.ifg.prototype.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "requisitos")
public class Requisito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @ManyToOne
    @JoinColumn(name = "projeto_codigo")
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

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
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

}
