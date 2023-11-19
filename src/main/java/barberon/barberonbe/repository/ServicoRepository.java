package barberon.barberonbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import barberon.barberonbe.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
