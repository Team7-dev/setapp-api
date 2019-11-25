package br.com.uniplan.pim.setappapi.dto;

import br.com.uniplan.pim.setappapi.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class AssembleiaDto {

    private Long id;

    private Date dataHoraCadastro;

    private String motivo;

    private Date dataHoraAgendamento;

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(Date dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
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
