package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AgendaDTO;
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
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
        List<Agenda> newAgendas = new ArrayList<>();
        for (AgendaDTO agendaDTO : agendasDTO) {
            Status status = statusRepository.findById(agendaDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status não encontrado"));

            Agenda newAgenda = new Agenda();
            newAgenda.setAgendaDiaSemana(agendaDTO.getAgendaDiaSemana());
            newAgenda.setAgendaHorarioInicio(agendaDTO.getAgendaHorarioInicio());
            newAgenda.setAgendaHorarioFim(agendaDTO.getAgendaHorarioFim());

            // Converte a lista de PausaDTO em uma lista de Pausa
            List<Pausa> pausas = new ArrayList<>();
            for (PausaDTO pausaDTO : agendaDTO.getPausas()) {
                Pausa pausa = new Pausa();
                pausa.setPausaId(pausaDTO.getPausaId());
                pausa.setPausaHorarioInicio(pausaDTO.getPausaHorarioInicio());
                pausa.setPausaHorarioFim(pausaDTO.getPausaHorarioFim());
                pausa.setAgenda(newAgenda); // Define a agenda da pausa como a nova agenda
                pausas.add(pausa);
            }
            newAgenda.setPausas(pausas);

            newAgenda.setBarbeiro(barbeiro);
            newAgenda.setStatus(status);

            newAgendas.add(agendaRepository.save(newAgenda));
        }
        return newAgendas;
    }

    // patch / update agenda - relacionando o BarbeiroId e AgendaId
    public Agenda updateAgenda(Long agendaId, AgendaDTO agendaDTO) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

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
            Status status = statusRepository.findById(agendaDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status não encontrado"));
            agenda.setStatus(status);
        }
        // Get the current list of pauses
        List<Pausa> currentPausas = agenda.getPausas();

        // Convert the list of PausaDTO to a list of Pausa ids
        List<Long> providedPausaIds = agendaDTO.getPausas().stream()
                .map(PausaDTO::getPausaId)
                .collect(Collectors.toList());

        // Find pauses that are in the current list but not in the provided list
        List<Pausa> pausesToRemove = currentPausas.stream()
                .filter(pausa -> !providedPausaIds.contains(pausa.getPausaId()))
                .collect(Collectors.toList());

        // Remove the pauses
        for (Pausa pausa : pausesToRemove) {
            pausaService.deletePausaById(pausa.getPausaId());
        }

        // Atualiza as pausas se elas foram fornecidas
        if (agendaDTO.getPausas() != null) {
            List<Pausa> existingPausas = agenda.getPausas();

            for (PausaDTO pausaDTO : agendaDTO.getPausas()) {
                Pausa pausa;
                if (pausaDTO.getPausaId() != null) {
                    // Se a pausa já existe, recupera a instância existente e atualiza
                    pausa = existingPausas.stream()
                            .filter(p -> p.getPausaId().equals(pausaDTO.getPausaId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Pausa não encontrada"));
                    pausa.setPausaHorarioInicio(pausaDTO.getPausaHorarioInicio());
                    pausa.setPausaHorarioFim(pausaDTO.getPausaHorarioFim());
                } else {
                    // Se a pausa é nova, cria
                    pausa = new Pausa();
                    pausa.setPausaHorarioInicio(pausaDTO.getPausaHorarioInicio());
                    pausa.setPausaHorarioFim(pausaDTO.getPausaHorarioFim());
                    pausa.setAgenda(agenda);
                    pausa = pausaService.savePausa(pausa); // Salva a nova pausa
                    // Adiciona a pausa à lista de pausas da agenda
                    existingPausas.add(pausa);
                }
            }
        }

        return agendaRepository.save(agenda);
    }

    public List<Agenda> getAgendasByBarbeiro(Long barbeiroId) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
        return agendaRepository.findByBarbeiro(barbeiro);
    }

    public List<Pausa> getPausasByAgenda(Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));
        return agenda.getPausas();
    }
}