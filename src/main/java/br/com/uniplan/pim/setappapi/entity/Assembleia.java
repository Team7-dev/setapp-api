package br.com.uniplan.pim.setappapi.entity;

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

    @Column(name = "DATA_HORA_AGENDADO")
    private Date dataHoraAgendado;

    @Column(name = "DATA_HORA_REALIZADO")
    private Date dataHoraRealizado;

    @Column(name = "DATA_HORA_CONCLUIDO")
    private Date dataHoraConcluido;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TIPO_ASSEMBLEIA")
    private TipoAssembleia tipoAssembleia;

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

    public Date getDataHoraAgendado() {
        return dataHoraAgendado;
    }

    public void setDataHoraAgendado(Date dataHoraAgendado) {
        this.dataHoraAgendado = dataHoraAgendado;
    }

    public Date getDataHoraRealizado() {
        return dataHoraRealizado;
    }

    public void setDataHoraRealizado(Date dataHoraRealizado) {
        this.dataHoraRealizado = dataHoraRealizado;
    }

    public Date getDataHoraConcluido() {
        return dataHoraConcluido;
    }

    public void setDataHoraConcluido(Date dataHoraConcluido) {
        this.dataHoraConcluido = dataHoraConcluido;
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

    public TipoAssembleia getTipoAssembleia() {
        return tipoAssembleia;
    }

    public void setTipoAssembleia(TipoAssembleia tipoAssembleia) {
        this.tipoAssembleia = tipoAssembleia;
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

