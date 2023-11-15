package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.repository.AgendaRepository;

import java.util.List;



@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda saveAgenda(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public List<Agenda> getAllAgendas() {
        return agendaRepository.findAll();
    }

    public Agenda getAgendaById(Long id) {
        return agendaRepository.findById(id).orElse(null);
    }

    public Agenda getAgendaByBarbeiroId(Long barbeiroId) {
        return agendaRepository.findByBarbeiro_Id(barbeiroId).orElse(null);
    }
    

    // Add more methods as needed
}
