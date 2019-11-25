package br.com.uniplan.pim.setappapi.dto;

import br.com.uniplan.pim.setappapi.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class OcorrenciaDto {

    private Long id;

    private Date dataHoraCadastro;

    private String ocorrencia;

    private String descricao;

    private Date dataHoraOcorrencia;

    private Date dataHoraConclusao;

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

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(Date dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public Date getDataHoraConclusao() {
        return dataHoraConclusao;
    }

    public void setDataHoraConclusao(Date dataHoraConclusao) {
        this.dataHoraConclusao = dataHoraConclusao;
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
