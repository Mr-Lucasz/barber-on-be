package barberon.barberonbe.service;

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
        return repository.save(barbeiro);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
