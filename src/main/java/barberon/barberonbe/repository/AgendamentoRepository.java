package barberon.barberonbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import barberon.barberonbe.DTO.RelatorioDTO;
import barberon.barberonbe.model.Agendamento;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    @Query("SELECT new barberon.barberonbe.DTO.RelatorioDTO(b.nome, s.servico_titulo, a.hora_inicio, s.servico_valor) " +
           "FROM agendamento a " +
           "JOIN barbeiro b ON a.barbeiro_id = b.id " +
           "JOIN servico s ON a.servico_id = s.servico_id")
    List<RelatorioDTO> findAllRelatorio();
}
