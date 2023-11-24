package barberon.barberonbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.repository.ServicoRepository;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    public List<Servico> findAll() {
        return repository.findAll();
    }

    public Servico findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Servico save(Servico servico) {
        return repository.save(servico);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void uptadeServiceById(long id, Servico servico) {

        servico = repository.findById(id).orElse(null);
        servico.setServicoTitulo(servico.getServicoTitulo());
        servico.setServicoDescricao(servico.getServicoDescricao());
        servico.setServicoValor(servico.getServicoValor());
        servico.setBarbeiro(servico.getBarbeiro());
        // servico.setImagem(servico.getImagem());
        repository.save(servico);
    }
}