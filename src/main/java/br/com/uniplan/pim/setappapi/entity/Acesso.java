package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ACESSO")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_ACESSO")
    private Date dataHoraAcesso;

    @Column(name = "SENTIDO")
    private String sentido;

    @Column(name = "AUTORIZADO")
    private Integer autorizado;

    @Column(name = "MOTIVO_NAO_AUTORIZADO")
    private String motivoNaoAutorizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UNIDADE")
    private Unidade unidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public void setDataHoraAcesso(Date dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public String getMotivoNaoAutorizado() {
        return motivoNaoAutorizado;
    }

    public void setMotivoNaoAutorizado(String motivoNaoAutorizado) {
        this.motivoNaoAutorizado = motivoNaoAutorizado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acesso job = (Acesso) o;
        return id.equals(job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
