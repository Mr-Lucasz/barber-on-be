package barberon.barberonbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import barberon.barberonbe.model.Agendamento;


@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


}
