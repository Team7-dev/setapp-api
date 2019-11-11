package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "UNIDADE")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BLOCO")
    private String bloco;

    @Column(name = "ANDAR")
    private String apartamento;

    @Column(name = "OCUPADO")
    private Integer ocupado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TIPO")
    private TipoUnidade tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public Integer getOcupado() {
        return ocupado;
    }

    public void setOcupado(Integer ocupado) {
        this.ocupado = ocupado;
    }

    public TipoUnidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidade tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unidade job = (Unidade) o;
        return id.equals(job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
