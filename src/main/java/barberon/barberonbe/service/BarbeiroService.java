package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.AgendaDTO;
import barberon.barberonbe.DTO.BarbeiroDTO;
import barberon.barberonbe.DTO.PausaDTO;
import barberon.barberonbe.DTO.ServicoDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbearia;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.repository.BarbeariaRepository;
import barberon.barberonbe.repository.BarbeiroRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Transactional
    public List<BarbeiroDTO> findAllBarbeirosWithAgendas() {
        List<Barbeiro> barbeiros = repository.findAll();
        return barbeiros.stream()
                .map(this::convertToDTOWithAgendas)
                .collect(Collectors.toList());
    }

    private BarbeiroDTO convertToDTOWithAgendas(Barbeiro barbeiro) {
        BarbeiroDTO dto = convertToDTO(barbeiro);
        barbeiro.getAgendas().size();
        List<AgendaDTO> agendaDTOs = barbeiro.getAgendas().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        dto.setAgendas(agendaDTOs);
        return dto;
    }

    public Barbeiro findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public BarbeiroDTO save(Barbeiro barbeiro) {
        Long barbeariaId = 1L;
        Barbearia barbearia = barbeariaRepository.findById(barbeariaId)
                .orElseThrow(() -> new RuntimeException("Barbearia com id " + barbeariaId + " não encontrada"));
        barbeiro.setBarbearia(barbearia);
        Barbeiro savedBarbeiro = repository.save(barbeiro);
        return convertToDTO(savedBarbeiro);
    }

    private BarbeiroDTO convertToDTO(Barbeiro barbeiro) {
        BarbeiroDTO dto = new BarbeiroDTO();
        if (barbeiro.getImagem() != null) {
            Long imagemId = barbeiro.getImagem().getImagemId();
            dto.setImagemId(imagemId);
        }

        dto.setId(barbeiro.getId());
        dto.setBarbeariaId(barbeiro.getBarbearia().getBarbeariaId());
        dto.setNome(barbeiro.getNome());
        dto.setDataNascimento(barbeiro.getDataNascimento());
        dto.setCpf(barbeiro.getCpf());
        dto.setTelefone(barbeiro.getTelefone());
        dto.setEmail(barbeiro.getEmail());
        dto.setSenha(barbeiro.getSenha());
        dto.setMediaAvaliacao(barbeiro.getMediaAvaliacao());
        List<AgendaDTO> agendaDTOs = barbeiro.getAgendas().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        dto.setAgendas(agendaDTOs);
        List<ServicoDTO> servicoDTOs = barbeiro.getServicos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        dto.setServicos(servicoDTOs);
        return dto;
    }

    private AgendaDTO convertToDTO(Agenda agenda) {
        AgendaDTO dto = new AgendaDTO();
        dto.setAgendaId(agenda.getAgendaId());
        dto.setAgendaDiaSemana(agenda.getAgendaDiaSemana());
        dto.setStatusNome(agenda.getStatus().getStatusNome());
        dto.setStatusId(agenda.getStatus().getId());
        dto.setAgendaHorarioInicio(agenda.getAgendaHorarioInicio());
        dto.setAgendaHorarioFim(agenda.getAgendaHorarioFim());
        List<PausaDTO> pausaDTOs = agenda.getPausas().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        dto.setPausas(pausaDTOs);
        return dto;
    }

    private ServicoDTO convertToDTO(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setServicoId(servico.getServicoId());
        dto.setServicoNome(servico.getServicoTitulo());
        dto.setServicoDescricao(servico.getServicoDescricao());
        dto.setServicoValor(servico.getServicoValor());
        dto.setServicoDuracao(servico.getServicoTempoMinuto().intValue());
        return dto;
    }

    private PausaDTO convertToDTO(Pausa pausa) {
        PausaDTO dto = new PausaDTO();
        dto.setPausaId(pausa.getPausaId());
        dto.setPausaHorarioInicio(pausa.getPausaHorarioInicio());
        dto.setPausaHorarioFim(pausa.getPausaHorarioFim());
        return dto;
    }

}