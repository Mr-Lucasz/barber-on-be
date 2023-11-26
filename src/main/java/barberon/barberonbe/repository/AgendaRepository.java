package barberon.barberonbe.repository;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByBarbeiroId(Long barbeiroId);
    List<Agenda> findByBarbeiro(Barbeiro barbeiro);
    List <Agenda> findByStatus(Status status);
    List <Agenda> findByStatusId(Long statusId);
    Optional<Agenda> findByAgendaIdAndBarbeiro(Long agendaId, Barbeiro barbeiro);
}