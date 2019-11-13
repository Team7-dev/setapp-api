package br.com.uniplan.pim.setappapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SITUACAO")
    private String situacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFIL_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Perfil perfil;

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario job = (Usuario) o;
        return id.equals(job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
