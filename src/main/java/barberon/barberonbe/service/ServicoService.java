package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.ServicoDTO;
import barberon.barberonbe.DTO.ServicoResponseDTO;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.ServicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    // public List<Servico> findAll() {
    //     return repository.findAll();
    // }

    public Servico findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Servico> addServicos(Long barbeiroId, List<ServicoDTO> servicoDTOs) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro com id " + barbeiroId + " não encontrado"));

        List<Servico> servicos = servicoDTOs.stream()
                .map(dto -> {
                    Servico servico = new Servico();
                    servico.setBarbeiro(barbeiro);
                    servico.setServicoTitulo(dto.getServicoTitulo());
                    servico.setServicoDescricao(dto.getServicoDescricao());
                    servico.setServicoTempoMinuto(dto.getServicoTempoMinuto());
                    servico.setServicoTempoHora(dto.getServicoTempoHora());
                    servico.setServicoValor(dto.getServicoValor());
                    return servico;
                })
                .collect(Collectors.toList());

        return repository.saveAll(servicos);
    }

        public List<ServicoResponseDTO> findAll() {
        return repository.findAll().stream()
            .map(servico -> new ServicoResponseDTO(
                servico.getServicoId(),
                servico.getBarbeiro().getId(),
                servico.getImagem(),
                servico.getServicoTitulo(),
                servico.getServicoDescricao(),
                servico.getServicoTempoHora(),
                servico.getServicoTempoMinuto(),
                servico.getServicoValor()
            ))
            .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    

    public void updateServiceById(long id, Servico servico) {
        Servico existingService = repository.findById(id).orElse(null);
        existingService.setServicoTitulo(servico.getServicoTitulo());
        existingService.setServicoDescricao(servico.getServicoDescricao());
        existingService.setServicoTempoHora(servico.getServicoTempoHora());
        existingService.setServicoTempoMinuto(servico.getServicoTempoMinuto());
        existingService.setServicoValor(servico.getServicoValor());
        existingService.setBarbeiro(servico.getBarbeiro());
        // existingService.setImagem(servico.getImagem());
        repository.save(existingService);
    }
}