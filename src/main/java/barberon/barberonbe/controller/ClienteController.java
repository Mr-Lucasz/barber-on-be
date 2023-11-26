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

import barberon.barberonbe.DTO.ClienteDTO;
import barberon.barberonbe.model.Cliente;
import barberon.barberonbe.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4000")

public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ClienteDTO save(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}

	@GetMapping
	public List<Cliente> findAll() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public Cliente getById(@PathVariable long id) {
		return clienteService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable long id, @RequestBody Cliente cliente) {
		ClienteDTO updatedCliente = clienteService.update(id, cliente);
		return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		clienteService.deleteById(id);
	}
}
