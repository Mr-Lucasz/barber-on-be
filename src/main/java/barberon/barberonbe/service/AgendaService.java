package barberon.barberonbe.service;

import org.springframework.stereotype.Service;

import java.util.List;


import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.repository.AgendaRepository;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;


    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Iterable<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findById(Long id) {
        return agendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agenda não encontrada"));
    }

    //Agenda por Barbeiro
    public List<Agenda> findByBarbeiro(Long id) {
        List<Agenda> agendas = agendaRepository.findByBarbeiroId(id);
        if (agendas.isEmpty()) {
            throw new ResourceNotFoundException("O Barbeiro com id " + id + " não possui agendas");
          
        } else {
             return agendas;
        }
    }

    public Agenda updateAgenda(@PathVariable Long id, @RequestBody Agenda newAgenda) {
        return agendaRepository.findById(id)
        .map(agenda -> {
            agenda.setAgendaDiaSemana(newAgenda.getAgendaDiaSemana());
            agenda.setAgendaHorarioInicio(newAgenda.getAgendaHorarioInicio());
            agenda.setAgendaHorarioFim(newAgenda.getAgendaHorarioFim());
            agenda.setBarbeiro(newAgenda.getBarbeiro());
            agenda.setStatus(newAgenda.getStatus());
            return agendaRepository.save(agenda);
        }).orElseGet(() -> {
            newAgenda.setAgendaId(id);
            return agendaRepository.save(newAgenda);
        });
    }

}