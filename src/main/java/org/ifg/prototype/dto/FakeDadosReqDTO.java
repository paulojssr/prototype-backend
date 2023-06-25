package org.ifg.prototype.dto;

public class FakeDadosReqDTO {
    Long codigoDadosRequisitos = 0L;
    String descricao;
    String tipo;
    int maxCaracteres;
    int bloco;
    int coluna;
    String informacoesAdicionais;

    public Long getCodigoDadosRequisitos() {
        return codigoDadosRequisitos;
    }

    public void setCodigoDadosRequisitos(Long codigoDadosRequisitos) {
        this.codigoDadosRequisitos = codigoDadosRequisitos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMaxCaracteres() {
        return maxCaracteres;
    }

    public void setMaxCaracteres(int maxCaracteres) {
        this.maxCaracteres = maxCaracteres;
    }

    public int getBloco() {
        return bloco;
    }

    public void setBloco(int bloco) {
        this.bloco = bloco;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public String getInformacoesAdicionais() {
        if (informacoesAdicionais == null)
            return "";
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }
}