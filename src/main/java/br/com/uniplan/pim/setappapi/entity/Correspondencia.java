package br.com.uniplan.pim.setappapi.entity;

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

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "DATA_HORA_PREVISAO")
    private Date dataHoraPrevisao;

    @Column(name = "DATA_HORA_POSTADO")
    private Date dataHoraPostado;

    @Column(name = "DATA_HORA_RECEBIDO")
    private Date dataHoraRecebido;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Date getDataHoraPrevisao() {
        return dataHoraPrevisao;
    }

    public void setDataHoraPrevisao(Date dataHoraPrevisao) {
        this.dataHoraPrevisao = dataHoraPrevisao;
    }

    public Date getDataHoraPostado() {
        return dataHoraPostado;
    }

    public void setDataHoraPostado(Date dataHoraPostado) {
        this.dataHoraPostado = dataHoraPostado;
    }

    public Date getDataHoraRecebido() {
        return dataHoraRecebido;
    }

    public void setDataHoraRecebido(Date dataHoraRecebido) {
        this.dataHoraRecebido = dataHoraRecebido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
