package barberon.barberonbe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.ClienteDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Cliente;
import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public ClienteDTO save(Cliente cliente) {
		Cliente savedCliente = repository.save(cliente);
		return convertToDTO(savedCliente);
	}

	private ClienteDTO convertToDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();

		dto.setId(cliente.getId());
		dto.setNome(cliente.getNome());

		java.util.Date utilDate = cliente.getDataNascimento();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		dto.setDataNascimento(sqlDate);

		dto.setCpf(cliente.getCpf());
		dto.setTelefone(cliente.getTelefone());
		dto.setEmail(cliente.getEmail());
		dto.setSenha(cliente.getSenha());

		return dto;
	}

	public ClienteDTO update(Long id, Cliente clienteAtualizado) {
		Cliente existingCliente = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));

		existingCliente.setNome(clienteAtualizado.getNome());
		existingCliente.setDataNascimento(clienteAtualizado.getDataNascimento());
		existingCliente.setCpf(clienteAtualizado.getCpf());
		existingCliente.setTelefone(clienteAtualizado.getTelefone());
		existingCliente.setEmail(clienteAtualizado.getEmail());
		existingCliente.setSenha(clienteAtualizado.getSenha());

		Cliente updatedCliente = repository.save(existingCliente);

		return convertToDTO(updatedCliente);
	}

}
