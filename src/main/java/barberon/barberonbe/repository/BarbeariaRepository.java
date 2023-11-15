package barberon.barberonbe.repository;

import barberon.barberonbe.model.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {
}