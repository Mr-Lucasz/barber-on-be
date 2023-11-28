package barberon.barberonbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barberon.barberonbe.DTO.AgendamentoDTO;
import barberon.barberonbe.model.Agendamento;
import barberon.barberonbe.service.AgendamentoService;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "http://localhost:4000")

public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agendamentoService;

	@PostMapping
	public AgendamentoDTO save(@RequestBody Agendamento agendamento) {
		return agendamentoService.save(agendamento);
	}

	@GetMapping
	public List<Agendamento> findAll() {
		return agendamentoService.findAll();
	}

	@GetMapping("/{id}")
	public Agendamento getById(@PathVariable long id) {
		return agendamentoService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AgendamentoDTO> update(@PathVariable long id, @RequestBody Agendamento agendamento) {
		AgendamentoDTO updatedAgendamento = agendamentoService.update(id, agendamento);
		return new ResponseEntity<>(updatedAgendamento, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		agendamentoService.deleteById(id);
	}
}
