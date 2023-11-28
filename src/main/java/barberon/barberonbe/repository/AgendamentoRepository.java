package barberon.barberonbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import barberon.barberonbe.DTO.RelatorioDTO;
import barberon.barberonbe.model.Agendamento;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    @Query("SELECT new barberon.barberonbe.DTO.RelatorioDTO(b.barbeiroNome, s.servicoTitulo, a.agendamentoHorarioInicio, s.servicoValor) " +
           "FROM Agendamento a " +
           "JOIN Barbeiro b ON a.agendamentoBarbeiroID = b.barbeiroID " +
           "JOIN Servico s ON a.agendamentoServicoID = s.servicoID")
    List<RelatorioDTO> findAllRelatorio();
}
