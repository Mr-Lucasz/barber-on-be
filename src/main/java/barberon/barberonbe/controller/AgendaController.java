package barberon.barberonbe.controller;

import org.springframework.web.bind.annotation.RestController;

import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.service.AgendaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping("/api/agendas")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Agenda>> getAgendas() {
        return ResponseEntity.ok(agendaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Agenda> createAgenda(@RequestBody Agenda agenda) {
        return ResponseEntity.ok(agendaService.save(agenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getAgenda(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.findById(id));
    }

    // Get Agendas Barbeiro
    @GetMapping("/barbeiro/{id}")
    public ResponseEntity<Iterable<Agenda>> getAgendasBarbeiro(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.findByBarbeiro(id));
    }

    @PutMapping("/agendas/{id}")
    public Agenda updateAgenda(@PathVariable Long id, @RequestBody Agenda newAgenda) {
        return agendaService.updateAgenda(id, newAgenda);
    }
}