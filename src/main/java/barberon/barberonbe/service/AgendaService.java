package barberon.barberonbe.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.AgendaRepository;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.StatusRepository;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final StatusRepository statusRepository;

    public AgendaService(AgendaRepository agendaRepository, BarbeiroRepository barbeiroRepository,
            StatusRepository statusRepository) {
        this.agendaRepository = agendaRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.statusRepository = statusRepository;
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

    // Agenda por Barbeiro
    public List<Agenda> findByBarbeiro(Long id) {
        List<Agenda> agendas = agendaRepository.findByBarbeiroId(id);
        if (agendas.isEmpty()) {
            throw new ResourceNotFoundException("O Barbeiro com id " + id + " não possui agendas");

        } else {
            return agendas;
        }
    }

    public void adicionarAgendaAoBarbeiro(Long barbeiroId, Agenda novaAgenda) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new NoSuchElementException("Barbeiro não encontrado"));
        barbeiro.getAgendas().add(novaAgenda);
        barbeiroRepository.save(barbeiro);
    }

}