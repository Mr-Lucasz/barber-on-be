package barberon.barberonbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import barberon.barberonbe.DTO.AgendamentoDTO;
import barberon.barberonbe.model.Agendamento;
import barberon.barberonbe.repository.AgendamentoRepository;

public class AgendamentoService {
	@Autowired
	private AgendamentoRepository repository;

	public List<Agendamento> findAll() {
		return repository.findAll();
	}

	public Agendamento findById(long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public AgendamentoDTO save(Agendamento agendamento) {
		Agendamento savedCliente = repository.save(agendamento);
		return convertToDTO(savedCliente);
	}

	private AgendamentoDTO convertToDTO(Agendamento agendamento) {
		AgendamentoDTO dto = new AgendamentoDTO();

		dto.setId(agendamento.getId());
		dto.setBarbeiroId(agendamento.getBarbeiro());
		dto.setHoraInicio(agendamento.getHoraInicio());
		dto.setHoraFim(agendamento.getHoraFim());
		dto.setServicoId(agendamento.getServico());
		dto.setClienteId(agendamento.getCliente());
		dto.setStatusId(agendamento.getStatus());

		return dto;
	}

	public AgendamentoDTO update(Long id, Agendamento agendamentoAtualizado) {
		Agendamento existingAgendamento = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));

		existingAgendamento.setBarbeiro(agendamentoAtualizado.getBarbeiro());
		existingAgendamento.setHoraInicio(agendamentoAtualizado.getHoraInicio());
		existingAgendamento.setHoraFim(agendamentoAtualizado.getHoraFim());
		existingAgendamento.setServico(agendamentoAtualizado.getServico());
		existingAgendamento.setCliente(agendamentoAtualizado.getCliente());
		existingAgendamento.setStatus(agendamentoAtualizado.getStatus());

		Agendamento updatedAgendamento = repository.save(existingAgendamento);

		return convertToDTO(updatedAgendamento);
	}
}
