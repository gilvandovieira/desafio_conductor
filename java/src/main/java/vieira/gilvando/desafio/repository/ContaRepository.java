package vieira.gilvando.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import vieira.gilvando.desafio.model.Conta;

public interface ContaRepository extends CrudRepository<Conta, Long> {
}
