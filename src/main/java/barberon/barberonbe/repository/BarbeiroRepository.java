package barberon.barberonbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberon.barberonbe.model.Barbeiro;
import org.springframework.stereotype.Repository;


@Repository

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {


}
