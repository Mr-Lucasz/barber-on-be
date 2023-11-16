package barberon.barberonbe.controller;

import barberon.barberonbe.DTO.AgendaDTO;
import barberon.barberonbe.DTO.BarbeiroAgendasDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.service.AgendaService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agendas")
@CrossOrigin(origins = "http://localhost:4000")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping("/{agendaBarbeiroId}")
    public List<Agenda> createAgenda(@PathVariable Long agendaBarbeiroId, @RequestBody List<Agenda> agendas) {
        return agendas.stream()
                .map(agenda -> agendaService.saveAgendaBarber(agendaBarbeiroId, agenda))
                .collect(Collectors.toList());
    }

    @GetMapping("/with-barbeiros")
    public List<AgendaDTO> getAllAgendasWithBarbeiros() {
        return agendaService.getAllAgendasWithBarbeiros();
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

        Agenda savedAgenda = agendaService.saveAgendaBarber(barbeiroId, agenda);
        return ResponseEntity.ok(savedAgenda);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/barbeiro/{barbeiroId}")
    public BarbeiroAgendasDTO getAgendasByBarbeiroId(@PathVariable Long barbeiroId) {
        return agendaService.getAgendasByBarbeiroId(barbeiroId);
    }

}