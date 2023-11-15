package barberon.barberonbe.controller;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping
    public Agenda createAgenda(@RequestBody Agenda agenda) {
        return agendaService.saveAgenda(agenda);
    }

    @GetMapping
    public List<Agenda> getAllAgendas() {
        return agendaService.getAllAgendas();
    }

    @PutMapping("/barbeiro/{barbeiroId}")
    public ResponseEntity<Agenda> updateAgenda(@PathVariable Long barbeiroId, @RequestBody Agenda updatedAgenda) {
        Agenda agenda = agendaService.getAgendaByBarbeiroId(barbeiroId);
        if (agenda == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizar campos da agenda
        agenda.setAgendaDiaSemana(updatedAgenda.getAgendaDiaSemana());

        // Atualizar pausas
        agenda.getPausas().clear();
        agenda.getPausas().addAll(updatedAgenda.getPausas());
        updatedAgenda.getPausas().forEach(pausa -> pausa.setAgenda(agenda));

        Agenda savedAgenda = agendaService.saveAgenda(agenda);
        return ResponseEntity.ok(savedAgenda);
    }
}