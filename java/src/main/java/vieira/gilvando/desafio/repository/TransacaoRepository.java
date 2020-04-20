package vieira.gilvando.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import vieira.gilvando.desafio.model.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {
}
