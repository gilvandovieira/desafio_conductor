package vieira.gilvando.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vieira.gilvando.desafio.exceptions.ContaNaoEncontradaException;
import vieira.gilvando.desafio.exceptions.ValorMenorQueZeroException;
import vieira.gilvando.desafio.model.Conta;
import vieira.gilvando.desafio.model.Transacao;
import vieira.gilvando.desafio.repository.ContaRepository;
import vieira.gilvando.desafio.repository.TransacaoRepository;

import java.util.Calendar;

@Service
public class OperacaoService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    private Transacao nova() {
        final Transacao transacao = new Transacao();

        transacao.setDataTransacao(Calendar.getInstance().toString());
        return transacao;
    }

    public void depositInAccont(final Long idConta, final Double valor)
            throws ContaNaoEncontradaException, ValorMenorQueZeroException {
        final Transacao transacao = nova();

        if (valor <= 0.0) {
            throw new ValorMenorQueZeroException();
        }
        transacao.setValor(valor);

        final Conta conta = this.contaRepository.findById(idConta).orElseThrow(ContaNaoEncontradaException::new);

        transacao.setConta(conta);

        conta.setSaldo(conta.getSaldo() + valor);

        this.contaRepository.save(conta);
        this.transacaoRepository.save(transacao);

    }

    public void withdrawInAccount(final Long idConta, final Double valor)
            throws ContaNaoEncontradaException, ValorMenorQueZeroException {
        final Conta conta = this.contaRepository.findById(idConta).orElseThrow(ContaNaoEncontradaException::new);
        if (valor <= 0.0) {
            throw new ValorMenorQueZeroException();
        }

        final Transacao transacao = nova();
        transacao.setValor(-valor);
        transacao.setConta(conta);
        conta.setSaldo(conta.getSaldo() - valor);

        this.contaRepository.save(conta);
        this.transacaoRepository.save(transacao);
    }

    public Double balanceAccount(final Long idConta) throws ContaNaoEncontradaException {
        final Conta conta = this.contaRepository.findById(idConta).orElseThrow(ContaNaoEncontradaException::new);

        return conta.getSaldo();
    }
}
