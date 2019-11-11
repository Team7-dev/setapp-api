package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PERMISSAO")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERMISSAO")
    private String permissao;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        Permissao permissao1 = (Permissao) o;
        return Objects.equals(permissao, permissao1.permissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissao);
    }
}
