package barberon.barberonbe.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.service.AgendaService;
import barberon.barberonbe.service.BarbeiroService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

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



}