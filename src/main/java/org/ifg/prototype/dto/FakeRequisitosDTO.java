package org.ifg.prototype.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * Um DTO para a FakeRequisitosDTO entidade.
 * Crated by Daniel 04/06/2023.
 * Classe temporária não subir para o repositório!
 */
public class FakeRequisitosDTO {

    Long codigoDosRequisitos;
    String identificacao;
    String nome;
    String acoes;
    String validacoes;
    String projeto;

    List<FakeDadosReqDTO> listaDadosRequisitos;

    /**
     * Construtor para FakeRequisitosDTO.
     * Crated by Daniel 04/06/2023.
     */
    public FakeRequisitosDTO() {

        this.codigoDosRequisitos = 1L;
        this.identificacao = "RF001";
        this.nome = "Cadastro de Usuário";
        this.acoes = "Salvar, Novo";
        this.validacoes =  "O campo Nome é obrigatório, O campo E-mail é obrigatório, O campo Senha é obrigatório, O campo Confirmar Senha é obrigatório";
        this.projeto = "Projeto Teste";
        this.listaDadosRequisitos = new ArrayList<>();
        FakeDadosReqDTO fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(1L);
        fakeDadosReqDTO.setDescricao("Nome");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(1);
        fakeDadosReqDTO.setColuna(1);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(2L);
        fakeDadosReqDTO.setDescricao("E-mail");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(2);
        fakeDadosReqDTO.setColuna(1);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(3L);
        fakeDadosReqDTO.setDescricao("Senha");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setInformacoesAdicionais("o input deve ser do tipo password");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(3);
        fakeDadosReqDTO.setColuna(2);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(4L);
        fakeDadosReqDTO.setDescricao("Confirmar Senha");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(3);
        fakeDadosReqDTO.setColuna(2);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(5L);
        fakeDadosReqDTO.setDescricao("CPF");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setInformacoesAdicionais("deve conter o placeholder 000.000.000-00");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(5);
        fakeDadosReqDTO.setColuna(2);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(6L);
        fakeDadosReqDTO.setDescricao("RG");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(5);
        fakeDadosReqDTO.setColuna(2);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(7L);
        fakeDadosReqDTO.setDescricao("Data de Nascimento");
        fakeDadosReqDTO.setTipo("Data");
        fakeDadosReqDTO.setInformacoesAdicionais("deve conter o placeholder dd/mm/aaaa");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(7);
        fakeDadosReqDTO.setColuna(4);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(8L);
        fakeDadosReqDTO.setDescricao("Sexo");
        fakeDadosReqDTO.setTipo("Radio");
        fakeDadosReqDTO.setInformacoesAdicionais("deve conter as opções para marcar Feminino e Masculino");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(8);
        fakeDadosReqDTO.setColuna(1);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(9L);
        fakeDadosReqDTO.setDescricao("Telefone");
        fakeDadosReqDTO.setTipo("Texto");
        fakeDadosReqDTO.setInformacoesAdicionais("deve conter o placeholder (00) 00000-0000");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(9);
        fakeDadosReqDTO.setColuna(4);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(9L);
        fakeDadosReqDTO.setDescricao("Lista de Países");
        fakeDadosReqDTO.setTipo("Tabela");
        fakeDadosReqDTO.setInformacoesAdicionais("tabela deve ter 5 colunas, País, Sigla, Código Telefone, " +
                "Código ISO, Continente e preencha com os dados 10 registros de países fictícios");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(10);
        fakeDadosReqDTO.setColuna(1);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(10L);
        fakeDadosReqDTO.setDescricao("Botão Salvar");
        fakeDadosReqDTO.setTipo("Botão");
        fakeDadosReqDTO.setInformacoesAdicionais("deve estar escrito Salvar deve e ser do tipo submit");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(11);
        fakeDadosReqDTO.setColuna(1);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
        fakeDadosReqDTO = new FakeDadosReqDTO();
        fakeDadosReqDTO.setCodigoDadosRequisitos(11L);
        fakeDadosReqDTO.setDescricao("Botão Novo");
        fakeDadosReqDTO.setTipo("Botão");
        fakeDadosReqDTO.setInformacoesAdicionais("deve estar escrito Limpar e conter do tipo button");
        fakeDadosReqDTO.setMaxCaracteres(50);
        fakeDadosReqDTO.setBloco(11);
        fakeDadosReqDTO.setColuna(1);
        this.listaDadosRequisitos.add(fakeDadosReqDTO);
    }

    public Long getCodigoDosRequisitos() {
        return codigoDosRequisitos;
    }

    public void setCodigoDosRequisitos(Long codigoDosRequisitos) {
        this.codigoDosRequisitos = codigoDosRequisitos;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
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

    public List<FakeDadosReqDTO> getListaDadosRequisitos() {
        if (listaDadosRequisitos == null){
            listaDadosRequisitos = new ArrayList<>();
        }
        return listaDadosRequisitos;
    }

    public void setListaDadosRequisitos(List<FakeDadosReqDTO> listaDadosRequisitos) {
        this.listaDadosRequisitos = listaDadosRequisitos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
