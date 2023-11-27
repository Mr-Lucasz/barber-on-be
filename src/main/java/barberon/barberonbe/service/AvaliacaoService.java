package barberon.barberonbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AvaliacaoDTO;
import barberon.barberonbe.model.Avaliacao;
import barberon.barberonbe.repository.AvaliacaoRepository;


@Service
public class AvaliacaoService {
    @Autowired
	private AvaliacaoRepository repository;

	public List<Avaliacao> findAll() {
		return repository.findAll();
	}

	public Avaliacao findById(long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public AvaliacaoDTO save(Avaliacao avaliacao) {
		Avaliacao savedAvaliacao = repository.save(avaliacao);
		return convertToDTO(savedAvaliacao);
	}

	private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
		AvaliacaoDTO dto = new AvaliacaoDTO();

		dto.setId(avaliacao.getId());
		dto.setAvaliacaoNota(avaliacao.getAvaliacaoNota());
		dto.setAgendamento(avaliacao.getAgendamento());

		return dto;
	}

	public AvaliacaoDTO update(Long id, Avaliacao avaliacaoAtualizado) {
		Avaliacao existingAvaliacao = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Avaliacao não encontrado com o ID: " + id));

		existingAvaliacao.setId(avaliacaoAtualizado.getId());
		existingAvaliacao.setAvaliacaoNota(avaliacaoAtualizado.getAvaliacaoNota());
		existingAvaliacao.setAgendamento(avaliacaoAtualizado.getAgendamento());

		Avaliacao updatedAvaliacao = repository.save(existingAvaliacao);

		return convertToDTO(updatedAvaliacao);
	}
}
