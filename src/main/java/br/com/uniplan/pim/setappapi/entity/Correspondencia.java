package br.com.uniplan.pim.setappapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CORRESPONDENCIA")
public class Correspondencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_HORA_RECEBIDA")
    private Date dataHoraRecebida;

    @Column(name = "DATA_HORA_RETIRADA")
    private Date dataHoraRetirada;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondencia job = (Correspondencia) o;
        return id.equals(job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
