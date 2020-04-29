package vieira.gilvando.desafio;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import vieira.gilvando.desafio.model.Conta;
import vieira.gilvando.desafio.model.Pessoa;
import vieira.gilvando.desafio.model.Transacao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

@SpringBootTest
@ActiveProfiles("test")
public class ModelTests {

    @Test
    public void test_Pessoa_should_exist() {

        assertThat(new Pessoa()).isInstanceOf(Pessoa.class);

    }

    @Test
    public void test_Conta_should_exist() {

        assertThat(new Conta()).isInstanceOf(Conta.class);
    }

    @Test
    public void test_Transacao_should_exist() {

        assertThat(new Transacao()).isInstanceOf(Transacao.class);
    }
}
