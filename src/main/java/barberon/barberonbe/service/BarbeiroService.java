package barberon.barberonbe.service;

import barberon.barberonbe.Exception.GlobalExceptionHandler;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeiroService {
    @Autowired
    private BarbeiroRepository repository;

    public List<Barbeiro> findAll() {
        return repository.findAll();
    }

    public Barbeiro findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Barbeiro save(Barbeiro barbeiro) {
        validateBarbeiro(barbeiro);
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
        if (barbeiro.getMediaAvaliacao() == null) {
            throw new RuntimeException("Média de avaliação do barbeiro não pode ser vazio");
        }
        if (barbeiro.getMediaAvaliacao() < 0 || barbeiro.getMediaAvaliacao() > 5) {
            throw new RuntimeException("Média de avaliação do barbeiro deve estar entre 0 e 5");
        }
    }
}
