package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberon.barberonbe.DTO.AgendaDTO;
import barberon.barberonbe.DTO.PausaDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.service.AgendaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping("/api/barbeiros/{barbeiroId}/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<List<Agenda>> addAgenda(@PathVariable Long barbeiroId,
            @RequestBody List<AgendaDTO> agendasDTO) {
        List<Agenda> newAgendas = agendaService.addAgendas(barbeiroId, agendasDTO);
        return new ResponseEntity<>(newAgendas, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> getAgendasByBarbeiro(@PathVariable Long barbeiroId) {
        List<Agenda> agendas = agendaService.getAgendasByBarbeiro(barbeiroId);
        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }

    // patch / update agenda - relacionando o BarbeiroId e AgendaId
    @PatchMapping("/{agendaId}")
    public ResponseEntity<Agenda> updateAgenda(@PathVariable Long barbeiroId, @PathVariable Long agendaId,
            @RequestBody AgendaDTO agendaDTO) {
        Agenda updatedAgenda = agendaService.updateAgenda(agendaId, agendaDTO);
        return new ResponseEntity<>(updatedAgenda, HttpStatus.OK);
    }

    @GetMapping("/{agendaId}/pausas")
    public ResponseEntity<List<Pausa>> getPausasByAgenda(@PathVariable Long barbeiroId, @PathVariable Long agendaId) {
        List<Pausa> pausas = agendaService.getPausasByAgenda(agendaId);
        return new ResponseEntity<>(pausas, HttpStatus.OK);
    }

}