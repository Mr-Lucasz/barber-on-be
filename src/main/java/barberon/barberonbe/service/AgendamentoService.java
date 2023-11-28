package barberon.barberonbe.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AgendamentoDTO;
import barberon.barberonbe.DTO.AgendamentoListDTO;
import barberon.barberonbe.DTO.ClienteDTO;
import barberon.barberonbe.DTO.ServicoDTO;
import barberon.barberonbe.DTO.StatusDTO;
import barberon.barberonbe.model.Agendamento;
import barberon.barberonbe.model.Cliente;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.model.Status;
import barberon.barberonbe.repository.AgendamentoRepository;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.ClienteRepository;
import barberon.barberonbe.repository.ServicoRepository;
import barberon.barberonbe.repository.StatusRepository;

@Service
public class AgendamentoService {
	@Autowired
	private AgendamentoRepository repository;

	@Autowired
	private BarbeiroRepository barbeiroRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private StatusRepository statusRepository;

	public List<AgendamentoListDTO> findAll() {
		List<Agendamento> agendamentos = repository.findAll();
		return agendamentos.stream()
				.map(this::convertToListDTO)
				.collect(Collectors.toList());
	}

	private AgendamentoListDTO convertToListDTO(Agendamento agendamento) {
		AgendamentoListDTO listDTO = new AgendamentoListDTO();
	
		listDTO.setId(agendamento.getId());
		listDTO.setBarbeiroId(agendamento.getBarbeiro().getId());
		listDTO.setBarbeiroNome(agendamento.getBarbeiro().getNome());
		listDTO.setAgendamentos(Arrays.asList(convertToDTO(agendamento)));
	
		return listDTO;
	}

	public Agendamento findById(long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public AgendamentoListDTO save(AgendamentoDTO agendamentoDTO) {
		Agendamento agendamento = new Agendamento();

		agendamento.setBarbeiro(barbeiroRepository.findById(agendamentoDTO.getBarbeiroId()).orElse(null));
		agendamento.setHoraInicio(agendamentoDTO.getHoraInicio());
		agendamento.setHoraFim(agendamentoDTO.getHoraFim());
		agendamento.setServicos(agendamentoDTO.getServicos().stream()
		.map(servicoDTO -> servicoRepository.findById(servicoDTO.getServicoId()).orElse(null))
		.collect(Collectors.toList()));
		agendamento.setCliente(clienteRepository.findById(agendamentoDTO.getCliente().getId()).orElse(null));
		agendamento.setStatus(statusRepository.findById(agendamentoDTO.getStatus().getId()).orElse(null));

		Agendamento savedAgendamento = repository.save(agendamento);

		AgendamentoListDTO responseDTO = new AgendamentoListDTO();
		responseDTO.setId(savedAgendamento.getId());
		responseDTO.setBarbeiroId(savedAgendamento.getBarbeiro().getId());
		responseDTO.setBarbeiroNome(savedAgendamento.getBarbeiro().getNome());
		responseDTO.setAgendamentos(Arrays.asList(convertToDTO(savedAgendamento)));

		return responseDTO;
	}

	private AgendamentoDTO convertToDTO(Agendamento agendamento) {
		AgendamentoDTO dto = new AgendamentoDTO();
		

		dto.setBarbeiroId(agendamento.getBarbeiro().getId());
		dto.setBarbeiroNome(agendamento.getBarbeiro().getNome());
		dto.setHoraInicio(agendamento.getHoraInicio());
		dto.setHoraFim(agendamento.getHoraFim());
		dto.setServicos(agendamento.getServicos().stream()
		.map(this::convertToServicoDTO)
		.collect(Collectors.toList()));
		dto.setCliente(convertToClienteDTO(agendamento.getCliente()));
		dto.setStatus(convertToStatusDTO(agendamento.getStatus()));

		return dto;
	}

	private ServicoDTO convertToServicoDTO(Servico servico) {
		ServicoDTO dto = new ServicoDTO();

		dto.setServicoId(servico.getServicoId());
		dto.setServicoTitulo(servico.getServicoTitulo());
		dto.setServicoDescricao(servico.getServicoDescricao());
		dto.setServicoValor(servico.getServicoValor());

		return dto;
	}

	private ClienteDTO convertToClienteDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();

		dto.setId(cliente.getId());
		dto.setNome(cliente.getNome());
		dto.setTelefone(cliente.getTelefone());
		dto.setEmail(cliente.getEmail());

		return dto;
	}

	private StatusDTO convertToStatusDTO(Status status) {
		StatusDTO dto = new StatusDTO();

		dto.setId(status.getId());
		dto.setStatusNome(status.getStatusNome());

		return dto;
	}

	public AgendamentoDTO update(Long id, Agendamento agendamentoAtualizado) {
		Agendamento existingAgendamento = repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));

		existingAgendamento.setBarbeiro(agendamentoAtualizado.getBarbeiro());
		existingAgendamento.setHoraInicio(agendamentoAtualizado.getHoraInicio());
		existingAgendamento.setHoraFim(agendamentoAtualizado.getHoraFim());
		existingAgendamento.setServicos(agendamentoAtualizado.getServicos());
		existingAgendamento.setCliente(agendamentoAtualizado.getCliente());
		existingAgendamento.setStatus(agendamentoAtualizado.getStatus());

		Agendamento updatedAgendamento = repository.save(existingAgendamento);
		return convertToDTO(updatedAgendamento);
	}
}
