package br.com.uniplan.pim.setappapi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "BOLETO")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO_BOLETO")
    private String codigoBoleto;

    @Column(name = "DATA_HORA_GERADO")
    private Date dataHoraGerado;

    @Column(name = "DATA_HORA_VENCIMENTO")
    private Date dataHoraVencimento;

    @Column(name = "ARQUIVO")
    private String arquivo;

    @Column(name = "ATIVO")
    private Integer ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAGAMENTO")
    private FolhaPagamento folhaPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoBoleto() {
        return codigoBoleto;
    }

    public void setCodigoBoleto(String codigoBoleto) {
        this.codigoBoleto = codigoBoleto;
    }

    public Date getDataHoraGerado() {
        return dataHoraGerado;
    }

    public void setDataHoraGerado(Date dataHoraGerado) {
        this.dataHoraGerado = dataHoraGerado;
    }

    public Date getDataHoraVencimento() {
        return dataHoraVencimento;
    }

    public void setDataHoraVencimento(Date dataHoraVencimento) {
        this.dataHoraVencimento = dataHoraVencimento;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
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
        Boleto boleto = (Boleto) o;
        return Objects.equals(id, boleto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
