package barberon.barberonbe.service;

import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AgendaDTO;
import barberon.barberonbe.DTO.BarbeiroDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.AgendaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final BarbeiroService barbeiroService;
    private final StatusService statusService;

    public AgendaService(AgendaRepository agendaRepository, BarbeiroService barbeiroService,
            StatusService statusService) {
        this.agendaRepository = agendaRepository;
        this.barbeiroService = barbeiroService;
        this.statusService = statusService;
    }

    public List<AgendaDTO> saveAgendaBarber(Long agendaBarbeiroId, List<Agenda> agendas) {
        Barbeiro barbeiro = barbeiroService.findById(agendaBarbeiroId);
        if (barbeiro != null) {
            agendas.forEach(agenda -> {
                agenda.setBarbeiro(barbeiro);
                Status status = statusService.getStatusById(agenda.getStatus().getId());
                if (status != null) {
                    agenda.setStatus(status);
                    statusService.updateStatus(status);  // Use o método merge aqui
                } else {
                    throw new IllegalArgumentException("StatusId does not exist");
                }
            });
            List<Agenda> savedAgendas = agendaRepository.saveAll(agendas);
            return savedAgendas.stream().map(this::convertToAgendaDTO).collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("BarbeiroId does not exist");
        }
    }
    public List<AgendaDTO> getAllAgendas() {
        List<Agenda> agendas = agendaRepository.findAll();
        return agendas.stream().map(this::convertToAgendaDTO).collect(Collectors.toList());
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
        dto.setAgendaDiaSemana(agenda.getAgendaDiaSemana());
        dto.setAgendaHorarioInicio(agenda.getAgendaHorarioInicio());
        dto.setAgendaHorarioFim(agenda.getAgendaHorarioFim());
        dto.setStatusNome(agenda.getStatus().getStatusNome());
        return dto;
    }

    public BarbeiroDTO getAgendasByBarbeiroId(Long barbeiroId) {
        List<Agenda> agendas = agendaRepository.findByBarbeiro_Id(barbeiroId);
        List<AgendaDTO> agendaDTOs = agendas.stream()
                .map(this::convertToAgendaDTO)
                .collect(Collectors.toList());

        BarbeiroDTO barbeiroAgendasDTO = new BarbeiroDTO();
        barbeiroAgendasDTO.setBarbeariaId(barbeiroId);
        barbeiroAgendasDTO.setAgendas(agendaDTOs);
        return barbeiroAgendasDTO;
    }

    public Agenda updateAgendaStatus(Long agendaId, Long statusId) {
        Optional<Agenda> optionalAgenda = agendaRepository.findById(agendaId);
        if (optionalAgenda.isPresent()) {
            Agenda agenda = optionalAgenda.get();
            Status status = statusService.getStatusById(statusId);
            if (status != null) {
                agenda.setStatus(status);
                return agendaRepository.save(agenda);
            } else {
                throw new IllegalArgumentException("StatusId does not exist");
            }
        } else {
            throw new IllegalArgumentException("AgendaId does not exist");
        }
    }

}
