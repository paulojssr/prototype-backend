package org.ifg.prototype.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.ifg.prototype.entities.Projeto;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.entities.enums.ProjetoStatus;

import java.time.LocalDate;


/**
 *  Um DTO para a Projeto entidade.
 */
public class ProjetoDTO {

    private Long codigo;
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataFim;
    private Usuario usuario;
    private Long codigoUsuario;
    private ProjetoStatus status;

    /**
     *
     * @param projeto
     */
    public ProjetoDTO(Projeto projeto) {
        this.codigo = projeto.getCodigo();
        this.nome = projeto.getNome();
        this.descricao = projeto.getDescricao();
        this.dataInicio = projeto.getDataInicio();
        this.dataFim = projeto.getDataFim();
        this.usuario = projeto.getUsuario();
        this.status = projeto.getStatus();
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
        this.dataFim = projeto.getDataFim();
        this.usuario = projeto.getUsuario();
        this.codigoUsuario = codigoUsuario;
        this.status = projeto.getStatus();
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
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

    public ProjetoStatus getStatus() {
        return status;
    }

    public void setStatus(ProjetoStatus status) {
        this.status = status;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}