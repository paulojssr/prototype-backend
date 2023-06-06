package org.ifg.prototype.dto;

import org.ifg.prototype.entities.Prompt;
import org.ifg.prototype.entities.Usuario;

import java.time.LocalDate;

/**
 * Um DTO para a Prompt entidade.
 * Crated by Daniel 04/06/2023.
 */
public class PromptDTO {

    private Long codigo;
    private String nome;
    private LocalDate dataCriacao;
    private String promptModelo;
    private Usuario usuario;
    private Long codigoUsuario;

    public PromptDTO(Prompt prompt) {
        this.codigo = prompt.getCodigo();
        this.nome = prompt.getNome();
        this.dataCriacao = prompt.getDataCriacao();
        this.promptModelo = prompt.getPromptModelo();
        this.usuario = prompt.getUsuario();
    }

    public PromptDTO(Prompt prompt, Long codigoUsuario) {
        this.codigo = prompt.getCodigo();
        this.nome = prompt.getNome();
        this.dataCriacao = prompt.getDataCriacao();
        this.promptModelo = prompt.getPromptModelo();
        this.usuario = prompt.getUsuario();
        this.codigoUsuario = codigoUsuario;
    }

    public PromptDTO() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getPromptModelo() {
        return promptModelo;
    }

    public void setPromptModelo(String promptModelo) {
        this.promptModelo = promptModelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}