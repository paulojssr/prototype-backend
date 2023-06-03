package org.ifg.prototype.dto;

import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.entities.Usuario;

import java.time.LocalDateTime;


/**
 *  Um DTO para a Projeto entidade.
 */
public class ProjetoDTO {

    private Long codigo;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private Usuario usuario;
    private Long codigoUsuario;

    /**
     *
     * @param projeto
     */
    public ProjetoDTO(Projeto projeto) {
        this.codigo = projeto.getCodigo();
        this.nome = projeto.getNome();
        this.descricao = projeto.getDescricao();
        this.dataInicio = projeto.getDataInicio();
        this.usuario = projeto.getUsuario();
    }

    /**
     *
     * @param projeto
     * @param codigoUsuario
     */
    public ProjetoDTO(Projeto projeto, Long codigoUsuario) {
        this.codigo = projeto.getCodigo();
        this.nome = projeto.getNome();
        this.descricao = projeto.getDescricao();
        this.dataInicio = projeto.getDataInicio();
        this.usuario = projeto.getUsuario();
        this.codigoUsuario = codigoUsuario;
    }

    /**
     *
     */
    public ProjetoDTO() {

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
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