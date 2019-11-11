package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "VISITANTE")
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "DATA_HORA_AGENDAMENTO")
    private Date dataHoraAgendamento;

    @Column(name = "DATA_HORA_EXPIRACAO")
    private Date dataHoraExpiracao;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTORIZADOR")
    private Pessoa autorizador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VISITANTE")
    private Pessoa visitante;

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

    public Date getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(Date dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public Date getDataHoraExpiracao() {
        return dataHoraExpiracao;
    }

    public void setDataHoraExpiracao(Date dataHoraExpiracao) {
        this.dataHoraExpiracao = dataHoraExpiracao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pessoa getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(Pessoa autorizador) {
        this.autorizador = autorizador;
    }

    public Pessoa getVisitante() {
        return visitante;
    }

    public void setVisitante(Pessoa visitante) {
        this.visitante = visitante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitante visitante = (Visitante) o;
        return Objects.equals(id, visitante.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
