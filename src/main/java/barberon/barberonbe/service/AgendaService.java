package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AgendaDTO;
import barberon.barberonbe.DTO.AgendaRequest;
import barberon.barberonbe.DTO.PausaDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.AgendaRepository;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.StatusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PausaService pausaService;

    public List<Agenda> addAgendas(Long barbeiroId, List<AgendaDTO> agendasDTO) {
        Barbeiro barbeiro = findBarbeiroById(barbeiroId);
        List<Agenda> newAgendas = createAgendasFromDTO(agendasDTO, barbeiro);
        return newAgendas;
    }

    public Agenda updateAgenda(Long agendaId, AgendaDTO agendaDTO) {
        Agenda agenda = findAgendaById(agendaId);
        updateAgendaData(agenda, agendaDTO);
        return agendaRepository.save(agenda);
    }

    public Agenda getAgendaById(Long agendaId) {
        return findAgendaById(agendaId);
    }

    public List<Agenda> getAgendasByBarbeiro(Long barbeiroId) {
        Barbeiro barbeiro = findBarbeiroById(barbeiroId);
        return agendaRepository.findByBarbeiro(barbeiro);
    }

    public List<Pausa> getPausasByAgenda(Long agendaId) {
        Agenda agenda = findAgendaById(agendaId);
        return agenda.getPausas();
    }

    public Agenda updateBarbeiroAgenda(Long barbeiroId, Long agendaId, AgendaRequest agendaRequest) {
        Barbeiro barbeiro = findBarbeiroById(barbeiroId);
        Agenda agenda = findAgendaByBarbeiro(agendaId, barbeiro);
        updatePausas(agenda, agendaRequest.getPausas());
        return agendaRepository.save(agenda);
    }

    private Barbeiro findBarbeiroById(Long barbeiroId) {
        return barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
    }

    private Agenda findAgendaById(Long agendaId) {
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));
    }

    private Agenda findAgendaByBarbeiro(Long agendaId, Barbeiro barbeiro) {
        return agendaRepository
                .findByAgendaIdAndBarbeiro(agendaId, barbeiro)
                .orElseThrow(
                        () -> new RuntimeException("Agenda não encontrada para este barbeiro: " + barbeiro.getId()));
    }

    private List<Agenda> createAgendasFromDTO(List<AgendaDTO> agendasDTO, Barbeiro barbeiro) {
        List<Agenda> newAgendas = new ArrayList<>();
        for (AgendaDTO agendaDTO : agendasDTO) {
            Status status = findStatusById(agendaDTO.getStatusId());
            Agenda newAgenda = createAgendaFromDTO(agendaDTO, barbeiro, status);
            newAgendas.add(agendaRepository.save(newAgenda));
        }
        return newAgendas;
    }

    private Agenda createAgendaFromDTO(AgendaDTO agendaDTO, Barbeiro barbeiro, Status status) {
        Agenda newAgenda = new Agenda();
        newAgenda.setAgendaDiaSemana(agendaDTO.getAgendaDiaSemana());
        newAgenda.setAgendaHorarioInicio(agendaDTO.getAgendaHorarioInicio());
        newAgenda.setAgendaHorarioFim(agendaDTO.getAgendaHorarioFim());
        newAgenda.setPausas(createPausasFromDTO(agendaDTO.getPausas(), newAgenda));
        newAgenda.setBarbeiro(barbeiro);
        newAgenda.setStatus(status);
        return newAgenda;
    }

    private List<Pausa> createPausasFromDTO(List<PausaDTO> pausasDTO, Agenda newAgenda) {
        List<Pausa> pausas = new ArrayList<>();
        for (PausaDTO pausaDTO : pausasDTO) {
            Pausa pausa = new Pausa();
            pausa.setPausaId(pausaDTO.getPausaId());
            pausa.setPausaHorarioInicio(pausaDTO.getPausaHorarioInicio());
            pausa.setPausaHorarioFim(pausaDTO.getPausaHorarioFim());
            pausa.setAgenda(newAgenda);
            pausas.add(pausa);
        }
        return pausas;
    }

    private Status findStatusById(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    private void updateAgendaData(Agenda agenda, AgendaDTO agendaDTO) {
        if (agendaDTO.getAgendaDiaSemana() != null) {
            agenda.setAgendaDiaSemana(agendaDTO.getAgendaDiaSemana());
        }
        if (agendaDTO.getAgendaHorarioInicio() != null) {
            agenda.setAgendaHorarioInicio((agendaDTO.getAgendaHorarioInicio()));
        }
        if (agendaDTO.getAgendaHorarioFim() != null) {
            agenda.setAgendaHorarioFim((agendaDTO.getAgendaHorarioFim()));
        }
        if (agendaDTO.getStatusId() != null) {
            Status status = findStatusById(agendaDTO.getStatusId());
            agenda.setStatus(status);
        }
        if (agendaDTO.getPausas() != null) {
            updatePausas(agenda, agendaDTO.getPausas());
        }
    }

    private void updatePausas(Agenda agenda, List<PausaDTO> pausasDTO) {
        List<Pausa> currentPausas = agenda.getPausas();
        List<Long> providedPausaIds = pausasDTO.stream()
                .map(PausaDTO::getPausaId)
                .collect(Collectors.toList());

        List<Pausa> pausesToRemove = currentPausas.stream()
                .filter(pausa -> !providedPausaIds.contains(pausa.getPausaId()))
                .collect(Collectors.toList());

        for (Pausa pausa : pausesToRemove) {
            pausaService.deletePausaById(pausa.getPausaId());
        }

        if (pausasDTO != null) {
            List<Pausa> existingPausas = agenda.getPausas();

            for (PausaDTO pausaDTO : pausasDTO) {
                Pausa pausa = findExistingPausa(existingPausas, pausaDTO.getPausaId());

                if (pausa != null) {
                    updatePausaData(pausa, pausaDTO);
                } else {
                    pausa = createPausaFromDTO(pausaDTO, agenda);
                    existingPausas.add(pausaService.savePausa(pausa));
                }
            }
        }
    }

    private Pausa findExistingPausa(List<Pausa> pausas, Long pausaId) {
        if (pausaId == null) {
            return null;
        }
        return pausas.stream()
                .filter(p -> p.getPausaId().equals(pausaId))
                .findFirst()
                .orElse(null);
    }

    private void updatePausaData(Pausa pausa, PausaDTO dto) {
        pausa.setPausaHorarioInicio(dto.getPausaHorarioInicio());
        pausa.setPausaHorarioFim(dto.getPausaHorarioFim());
    }

    private Pausa createPausaFromDTO(PausaDTO pausaDTO, Agenda agenda) {
        Pausa pausa = new Pausa();
        pausa.setPausaHorarioInicio(pausaDTO.getPausaHorarioInicio());
        pausa.setPausaHorarioFim(pausaDTO.getPausaHorarioFim());
        pausa.setAgenda(agenda);
        return pausa;
    }
}