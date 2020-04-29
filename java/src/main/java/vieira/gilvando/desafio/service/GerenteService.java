package vieira.gilvando.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vieira.gilvando.desafio.exceptions.ContaNaoEncontradaException;
import vieira.gilvando.desafio.model.Conta;
import vieira.gilvando.desafio.model.Pessoa;
import vieira.gilvando.desafio.repository.ContaRepository;
import vieira.gilvando.desafio.repository.PessoaRepository;

import java.util.Calendar;

@Service
public class GerenteService {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ContaRepository contaRepository;

    public void createPessoa(String nome, String cpf, String dataNascimento) {
        Pessoa pessoa = new Pessoa();

        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setNome(nome);

        this.pessoaRepository.save(pessoa);
    }

    public void createAccount(Pessoa pessoa, int tipoConta) {
        Conta conta = new Conta();
        conta.setPessoa(pessoa);
        conta.setFlagAtivo(true);
        conta.setTipoConta(tipoConta);
        conta.setLimiteSaqueDiario(tipoConta * 2000.0);
        conta.setDataCriacao(Calendar.getInstance().getTime().toString());

        this.contaRepository.save(conta);
    }

    public void blockAccout(Long idConta) throws ContaNaoEncontradaException {
        Conta conta = this.contaRepository.findById(idConta).orElseThrow(ContaNaoEncontradaException::new);

        conta.setFlagAtivo(false);

        this.contaRepository.save(conta);

    }
}
