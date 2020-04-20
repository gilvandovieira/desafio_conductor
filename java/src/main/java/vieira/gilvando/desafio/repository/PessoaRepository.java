package vieira.gilvando.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import vieira.gilvando.desafio.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
