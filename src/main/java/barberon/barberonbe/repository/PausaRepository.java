package barberon.barberonbe.repository;

import barberon.barberonbe.model.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface PausaRepository extends JpaRepository<Pausa, Long> {
    Pausa findByAgendaId(Long agendaId);
}
