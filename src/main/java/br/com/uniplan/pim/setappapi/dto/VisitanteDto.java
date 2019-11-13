package br.com.uniplan.pim.setappapi.dto;

import br.com.uniplan.pim.setappapi.entity.Unidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class VisitanteDto {

    private Long id;

    private Date dataHoraCadastro;

    private String nome;

    private String cpf;

    private String situacao;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Unidade unidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

}
