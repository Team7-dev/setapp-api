package br.com.uniplan.pim.setappapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ASSEMBLEIA")
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "MOTIVO")
    private String motivo;

    @Column(name = "DATA_HORA_AGENDAMENTO")
    private Date dataHoraAgendamento;

    @Column(name = "DATA_HORA_CONCLUSAO")
    private Date dataHoraConclusao;

    @Column(name = "SITUACAO")
    private String situacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assembleia visitante = (Assembleia) o;
        return Objects.equals(id, visitante.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

