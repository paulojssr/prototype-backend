package org.ifg.prototype.entities.enums;

/**
 * Criado por Daniel em 03/06/2023.
 */
public enum ProjetoStatus {

    /**
     * Define os valores que serão utilizados no banco de dados
     */
    ATIVO("Ativo"),
    INATIVO("Inativo");


    private String descricao;

    ProjetoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}