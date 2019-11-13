package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PERFIL")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERFIL")
    private String perfil;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
