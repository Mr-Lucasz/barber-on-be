package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import barberon.barberonbe.DTO.ServicoDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.ServicoRepository;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServicoService {

    @Autowired
    private ServicoRepository serviceRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    private Barbeiro findBarbeiroById(Long barbeiroId) {
        return barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
    }
    private Servico findServicoById(Long servicoId) {
        return serviceRepository.findById(servicoId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    private Servico findServiceByBarbeiro(Long servicoId, Barbeiro barbeiro) {
        return serviceRepository.findByServicoIdAndBarbeiro(servicoId, barbeiro)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public void deleteServico(Long servicoId) {
        serviceRepository.deleteById(servicoId);
    }

   public Servico updateBarbeiroServico (Long barbeiroId, Long servicoId){
         Barbeiro barbeiro = findBarbeiroById(barbeiroId);
         Servico servico = findServicoById(servicoId);
         servico.setBarbeiro(barbeiro);
         return serviceRepository.save(servico);
    }

    public List<Servico> getServicosByBarbeiro(Long barbeiroId) {
        Barbeiro barbeiro = findBarbeiroById(barbeiroId);
        return serviceRepository.findByBarbeiro(barbeiro);   
    }

    private void updateServicoData(Servico servico, ServicoDTO servicoDTO) {

        if(servicoDTO.getServicoTitulo() != null) {
            servico.setServicoTitulo(servicoDTO.getServicoTitulo());
        }
        if(servicoDTO.getServicoDescricao() != null) {
            servico.setServicoDescricao(servicoDTO.getServicoDescricao());
        }
        if(servicoDTO.getServicoValor() != 0) {
            servico.setServicoValor(servicoDTO.getServicoValor());
        }
        if(servicoDTO.getServicoTempoHora() != 0) {
            servico.setServicoTempoHora(servicoDTO.getServicoTempoHora());
        }
        if(servicoDTO.getServicoTempoMinuto() != 0) {
            servico.setServicoTempoMinuto(servicoDTO.getServicoTempoMinuto());
        }

    }

    public List<Servico> addServicos(Long barbeiroId, List<ServicoDTO> servicoDTO) {
        Barbeiro barbeiro = findBarbeiroById(barbeiroId);
        List<Servico> newServicos = createServicosFromDTO(servicoDTO, barbeiro);
        return newServicos;
    }

    private List<Servico> createServicosFromDTO(List<ServicoDTO> servicosDTO, Barbeiro barbeiro) {
        List<Servico> newServicos = new ArrayList<>();
        for (ServicoDTO servicoDTO : servicosDTO) {
            Servico newServico = createServicoFromDTO(servicoDTO, barbeiro);
            newServicos.add(serviceRepository.save(newServico));
        }
        return newServicos;
    }

    private Servico createServicoFromDTO(ServicoDTO servicoDTO, Barbeiro barbeiro) {
        Servico newServico = new Servico();
        newServico.setServicoTitulo(servicoDTO.getServicoTitulo());
        newServico.setServicoDescricao(servicoDTO.getServicoDescricao());
        newServico.setServicoValor(servicoDTO.getServicoValor());
        newServico.setServicoTempoHora(servicoDTO.getServicoTempoHora());
        newServico.setServicoTempoMinuto(servicoDTO.getServicoTempoMinuto());
        newServico.setBarbeiro(barbeiro);
        return newServico;
    }



}