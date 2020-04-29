package vieira.gilvando.desafio.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "Contas")
@Entity(name = "Conta")
public class Conta {

    @Id
    @Column(name = "conta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @OneToOne /* (fetch = FetchType.LAZY) */
    private Pessoa pessoa;
    private Double saldo;
    private Double limiteSaqueDiario;
    private boolean flagAtivo;
    private int tipoConta;
    private String dataCriacao;

    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
    private List<Transacao> transacoes;

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public void setLimiteSaqueDiario(Double limiteSaqueDiario) {
        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public boolean isFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
