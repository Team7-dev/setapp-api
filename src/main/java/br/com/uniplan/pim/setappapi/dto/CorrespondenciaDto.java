package br.com.uniplan.pim.setappapi.dto;

import br.com.uniplan.pim.setappapi.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class CorrespondenciaDto {

    private Long id;

    private Date dataHoraCadastro;

    private String descricao;

    private Date dataHoraRecebida;

    private Date dataHoraRetirada;

    private String situacao;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraRecebida() {
        return dataHoraRecebida;
    }

    public void setDataHoraRecebida(Date dataHoraRecebida) {
        this.dataHoraRecebida = dataHoraRecebida;
    }

    public Date getDataHoraRetirada() {
        return dataHoraRetirada;
    }

    public void setDataHoraRetirada(Date dataHoraRetirada) {
        this.dataHoraRetirada = dataHoraRetirada;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
