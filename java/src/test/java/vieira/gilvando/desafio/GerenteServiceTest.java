package vieira.gilvando.desafio;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import vieira.gilvando.desafio.exceptions.ContaNaoEncontradaException;
import vieira.gilvando.desafio.model.Conta;
import vieira.gilvando.desafio.model.Pessoa;
import vieira.gilvando.desafio.repository.ContaRepository;
import vieira.gilvando.desafio.repository.PessoaRepository;
import vieira.gilvando.desafio.service.GerenteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GerenteServiceTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GerenteService gerenteService;

    @Test
    public void createAccountTest() {
        gerenteService.createPessoa("Gilvando", "012.345.678-90", "1990-01-01");
        Pessoa p = pessoaRepository.findById(1l).get();

        assertThat("Pessoa is created", p.getCpf(), is("012.345.678-90"));

        gerenteService.createAccount(p, 1);
        Conta c = contaRepository.findById(1l).get();
        assertThat("Conta is created", c.getPessoa().getNome(), is(p.getNome()));
    }

    @Test
    public void blockAccountTest() {
        gerenteService.createPessoa("Gilvando", "012.345.678-90", "1990-01-01");
        Pessoa p = pessoaRepository.findById(1l).get();
        gerenteService.createAccount(p, 1);
        Conta c = contaRepository.findById(1l).get();

        assertThat("Conta is unblocked", c.isFlagAtivo(), is(true));

        try {
            gerenteService.blockAccout(c.getIdConta());
            c = contaRepository.findById(1l).get();
            assertThat("Conta is blocked", c.isFlagAtivo(), is(false));
        } catch (ContaNaoEncontradaException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = ContaNaoEncontradaException.class)
    public void tryBlockAnAccountTest() throws ContaNaoEncontradaException {
        gerenteService.blockAccout(1l);
    }
}