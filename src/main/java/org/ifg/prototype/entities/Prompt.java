package org.ifg.prototype.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Criado par Daniel em 03/06/2023.
 * Classe que representa a entidade Prompt
 */
@Entity
@Table(name = "prompt")
public class Prompt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    /**
     * @Column - Define o nome da coluna no banco de dados
     * @JsonFormat - Define o formato da data
     * Variável do tipo LocalDate para armazenar a data de criação do projeto
     */
    @Column(name = "data_criacao")
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    /**
     * @Column - Define o nome da coluna no banco de dados
     * Variável do tipo String para armazenar o prompt modelo
     */
    @Column(name = "prompt_modelo")
    private String promptModelo;

    /**
     * @ManyToOne - Define o relacionamento entre as entidades
     * @JoinColumn - Define a coluna que será a chave estrangeira
     * Variável do tipo Usuario para armazenar o usuário que criou o projeto
     */
    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario usuario;

    public Prompt() {
    }

    public Long getCodigo() {
        return codigo = codigo == null ? 0 : codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prompt)) return false;
        Prompt prompt = (Prompt) o;
        return Objects.equals(codigo, prompt.codigo) && Objects.equals(nome, prompt.nome) && Objects.equals(promptModelo, prompt.promptModelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}