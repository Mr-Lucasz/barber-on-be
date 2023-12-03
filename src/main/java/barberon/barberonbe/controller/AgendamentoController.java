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
import barberon.barberonbe.DTO.AgendamentoListDTO;
import barberon.barberonbe.model.Agendamento;
import barberon.barberonbe.service.AgendamentoService;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "http://localhost:4000")

public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@PostMapping("/new")
	public ResponseEntity<AgendamentoListDTO> createAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
		AgendamentoListDTO newAgendamento = agendamentoService.save(agendamentoDTO);
		return new ResponseEntity<>(newAgendamento, HttpStatus.CREATED);

	}

	@GetMapping
	public List<AgendamentoListDTO> getAllAgendamentos() {
		return agendamentoService.findAll();
	}

	@GetMapping("/cliente/{clienteId}")
	public List<AgendamentoListDTO> getAllAgendamentosByClienteId(@PathVariable Long clienteId) {
		return agendamentoService.findAllByClienteId(clienteId);
	}

	@GetMapping("/barbeiro/{barbeiroId}")
	public List<AgendamentoListDTO> getAllAgendamentosByBarbeiroId(@PathVariable Long barbeiroId) {
		return agendamentoService.findAllByBarbeiroId(barbeiroId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Agendamento> getAgendamento(@PathVariable Long id) {
		Agendamento agendamento = agendamentoService.findById(id);
		if (agendamento != null) {
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
