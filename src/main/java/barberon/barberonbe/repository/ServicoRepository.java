package barberon.barberonbe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
        List<Servico> findByBarbeiroId(Long barbeiroId);

        List<Servico> findByBarbeiro(Barbeiro barbeiro);

        Optional<Servico> findByServicoIdAndBarbeiro(Long servicoId, Barbeiro barbeiro);
}
