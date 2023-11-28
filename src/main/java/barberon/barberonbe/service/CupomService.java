package barberon.barberonbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.CupomDTO;
import barberon.barberonbe.model.Cupom;
import barberon.barberonbe.repository.CupomRepository;

@Service
public class CupomService {

	@Autowired
	private CupomRepository repository;

	public List<Cupom> findAll() {
		return repository.findAll();
	}

	public Cupom findById(long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public CupomDTO save(Cupom cupom) {
		Cupom savedCupom = repository.save(cupom);
		return convertToDTO(savedCupom);
	}

	private CupomDTO convertToDTO(Cupom cupom) {
		CupomDTO dto = new CupomDTO();

		dto.setId(cupom.getId());
		dto.setQuantidade(cupom.getQuantidade());
		dto.setCodigo(cupom.getCodigo());
		dto.setPorcentagem(cupom.getPorcentagem());

		return dto;
	}

	public CupomDTO update(Long id, Cupom cupomAtualizado) {
		Cupom existingCupom = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cupom não encontrado com o ID: " + id));

		existingCupom.setQuantidade(cupomAtualizado.getQuantidade());
		existingCupom.setCodigo(cupomAtualizado.getCodigo());
		existingCupom.setPorcentagem(cupomAtualizado.getPorcentagem());

		Cupom updatedCupom = repository.save(existingCupom);

		return convertToDTO(updatedCupom);
	}
}
