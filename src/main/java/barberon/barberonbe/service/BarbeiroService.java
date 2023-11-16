package barberon.barberonbe.service;

import barberon.barberonbe.Exception.GlobalExceptionHandler;
import barberon.barberonbe.model.Barbearia;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.repository.BarbeariaRepository;
import barberon.barberonbe.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeiroService {
    @Autowired
    private BarbeiroRepository repository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public List<Barbeiro> findAll() {
        return repository.findAll();
    }

    public Barbeiro findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Barbeiro save(Barbeiro barbeiro) {
        Long barbeariaId = 52L;
        Barbearia barbearia = barbeariaRepository.findById(barbeariaId)
            .orElseThrow(() -> new RuntimeException("Barbearia com id " + barbeariaId + " não encontrada"));
    
        validateBarbeiro(barbeiro);
        barbeiro.setBarbearia(barbearia);
            
        return repository.save(barbeiro);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void updateMediaAvaliacao(Long id, Double novaAvaliacao) {
        Barbeiro barbeiro = repository.findById(id).orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
        Double mediaAvaliacaoAtual = barbeiro.getMediaAvaliacao();
        Double novaMediaAvaliacao = (mediaAvaliacaoAtual + novaAvaliacao) / 2;
        barbeiro.setMediaAvaliacao(novaMediaAvaliacao);
        repository.save(barbeiro);
    }

    public void validateBarbeiro(Barbeiro barbeiro) {
        if (barbeiro.getNome() == null || barbeiro.getNome().isEmpty()) {
            throw new RuntimeException("Nome do barbeiro não pode ser vazio");
        }
        if (barbeiro.getMediaAvaliacao() != null && (barbeiro.getMediaAvaliacao() < 0 || barbeiro.getMediaAvaliacao() > 5)) {
            throw new RuntimeException("Média de avaliação do barbeiro deve estar entre 0 e 5");
        }
     }
     
}
