package br.com.uniplan.pim.setappapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "COMUNICADO")
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        Comunicado visitante = (Comunicado) o;
        return Objects.equals(id, visitante.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
