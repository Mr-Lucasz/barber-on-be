package barberon.barberonbe.controller;

import barberon.barberonbe.DTO.AgendaDTO;
// import barberon.barberonbe.DTO.BarbeiroAgendasDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.service.AgendaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

// import java.util.Arrays;
import java.util.List;
// import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agendas")
@CrossOrigin(origins = "http://localhost:4000")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping("/barbeiro/{barbeiroId}/bulk")
    public ResponseEntity<List<AgendaDTO>> createAgendas(@PathVariable Long barbeiroId,
            @RequestBody List<Agenda> newAgendas) {
        List<AgendaDTO> agendas = agendaService.saveAgendaBarber(barbeiroId, newAgendas);
        if (agendas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(agendas);
        }
    }

    @GetMapping("/with-barbeiros")
    public List<AgendaDTO> getAllAgendasWithBarbeiros() {
        return agendaService.getAllAgendasWithBarbeiros();
    }

    @GetMapping
    public List<AgendaDTO> getAllAgendas() {
        return agendaService.getAllAgendas();
    }

    // @PutMapping("/barbeiro/{barbeiroId}")
    // public ResponseEntity<Agenda> updateAgenda(@PathVariable Long barbeiroId, @RequestBody Agenda updatedAgenda) {
    //     Agenda agenda = agendaService.getAgendaByBarbeiroId(barbeiroId);
    //     if (agenda == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     // Update agenda fields and status
    //     agenda.setAgendaDiaSemana(updatedAgenda.getAgendaDiaSemana());
    //     agenda.setStatus(updatedAgenda.getStatus()); // Add this line

    //     // Update breaks
    //     agenda.getPausas().clear();
    //     agenda.getPausas().addAll(updatedAgenda.getPausas());
    //     updatedAgenda.getPausas().forEach(pausa -> pausa.setAgenda(agenda));

    //     // Wrap the agenda in a list before saving
    //     List<AgendaDTO> savedAgendas = agendaService.saveAgendaBarber(barbeiroId, Arrays.asList(agenda));
    //     Agenda savedAgenda = savedAgendas.isEmpty() ? null : savedAgendas.get(0);
    //     return ResponseEntity.ok(savedAgenda);
    // }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @GetMapping("/barbeiro/{barbeiroId}")
    // public BarbeiroAgendasDTO getAgendasByBarbeiroId(@PathVariable Long barbeiroId) {
    //     return agendaService.getAgendasByBarbeiroId(barbeiroId);
    // }

}