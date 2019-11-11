package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TRANSACAO")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "DATA_HORA_TRANSACAO")
    private Date dataHoraTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAGAMENTO")
    private FolhaPagamento folhaPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataHoraTransacao() {
        return dataHoraTransacao;
    }

    public void setDataHoraTransacao(Date dataHoraTransacao) {
        this.dataHoraTransacao = dataHoraTransacao;
    }

    public FolhaPagamento getFolhaPagamento() {
        return folhaPagamento;
    }

    public void setFolhaPagamento(FolhaPagamento folhaPagamento) {
        this.folhaPagamento = folhaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao boleto = (Transacao) o;
        return Objects.equals(id, boleto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
