package org.ifg.prototype.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.ifg.prototype.entities.enums.ProjetoStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Criado par Daniel em 03/06/2023.
 */
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Id - Define a chave primária
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    /**
     * @Column - Define o nome da coluna no banco de dados
     */
    private String nome;

    /**
     * @Column - Define o nome da coluna no banco de dados
     */
    private String descricao;

    /**
     * @Column - Define o nome da coluna no banco de dados
     */
    @Column(name = "data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

    /**
     * @ManyToOne - Define o relacionamento entre as entidades
     * @JoinColumn - Define a coluna que será a chave estrangeira
     */
    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario usuario;

    /**
     * @Enumerated - Define o tipo de enumeração
     */
    @Enumerated(EnumType.STRING)
    private ProjetoStatus status;

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo = codigo == null ? 0 : codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public ProjetoStatus getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(ProjetoStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Projeto))
            return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(codigo, projeto.codigo) && Objects.equals(nome, projeto.nome)
                && Objects.equals(usuario, projeto.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}