package br.com.uniplan.pim.setappapi.dto;

import br.com.uniplan.pim.setappapi.entity.AreaReserva;
import br.com.uniplan.pim.setappapi.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class ReservaDto {

    private Long id;

    private Date dataHoraCadastro;

    private Date dataHoraInicio;

    private Date dataHoraFim;

    private String situacao;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AreaReserva areaReserva;

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

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public AreaReserva getAreaReserva() {
        return areaReserva;
    }

    public void setAreaReserva(AreaReserva areaReserva) {
        this.areaReserva = areaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
