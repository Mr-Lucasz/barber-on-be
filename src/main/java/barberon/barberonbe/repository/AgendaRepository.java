package barberon.barberonbe.repository;

import barberon.barberonbe.model.Agenda;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByBarbeiroId(Long barbeiroId);
}