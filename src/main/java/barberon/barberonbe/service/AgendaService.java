package barberon.barberonbe.service;

import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AgendaDTO;
import barberon.barberonbe.DTO.BarbeiroAgendasDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.repository.AgendaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final BarbeiroService barbeiroService;

    public AgendaService(AgendaRepository agendaRepository, BarbeiroService barbeiroService) {
        this.agendaRepository = agendaRepository;
        this.barbeiroService = barbeiroService;
    }

    public Agenda saveAgendaBarber(Long agendaBarbeiroId, Agenda agenda) {
        Barbeiro barbeiro = barbeiroService.findById(agendaBarbeiroId);
        if (barbeiro != null) {
            agenda.setBarbeiro(barbeiro);
            return agendaRepository.save(agenda);
        } else {
            throw new IllegalArgumentException("BarbeiroId does not exist");
        }
    }

    public List<Agenda> getAllAgendas() {
        return agendaRepository.findAll();
    }

    public Agenda getAgendaById(Long id) {
        return agendaRepository.findById(id).orElse(null);
    }

    public Agenda getAgendaByBarbeiroId(Long barbeiroId) {
        List<Agenda> agendas = agendaRepository.findByBarbeiro_Id(barbeiroId);
        return agendas.isEmpty() ? null : agendas.get(0);
    }

    public List<AgendaDTO> getAllAgendasWithBarbeiros() {
        List<Agenda> agendas = agendaRepository.findAll();
        return agendas.stream().map(this::convertToAgendaDTO).collect(Collectors.toList());
    }

    private AgendaDTO convertToAgendaDTO(Agenda agenda) {
        AgendaDTO dto = new AgendaDTO();
        dto.setAgendaId(agenda.getAgendaId());
        // dto.setAgendaBarbeiroId(agenda.getBarbeiro().getId());
        dto.setAgendaDiaSemana(agenda.getAgendaDiaSemana());
        dto.setAgendaHorarioInicio(agenda.getAgendaHorarioInicio());
        dto.setAgendaHorarioFim(agenda.getAgendaHorarioFim());
        return dto;
    }

    public BarbeiroAgendasDTO getAgendasByBarbeiroId(Long barbeiroId) {
        List<Agenda> agendas = agendaRepository.findByBarbeiro_Id(barbeiroId);
        List<AgendaDTO> agendaDTOs = agendas.stream()
                .map(this::convertToAgendaDTO)
                .collect(Collectors.toList());
    
        BarbeiroAgendasDTO barbeiroAgendasDTO = new BarbeiroAgendasDTO();
        barbeiroAgendasDTO.setBarbeiroId(barbeiroId);
        barbeiroAgendasDTO.setAgendas(agendaDTOs);
        return barbeiroAgendasDTO;
    }

}
