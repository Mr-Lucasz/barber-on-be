package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import barberon.barberonbe.model.Barbeiro;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import barberon.barberonbe.repository.BarbeiroRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/barber")
public class BarbeiroController {

    @Autowired
    private BarbeiroRepository repository;

    @PostMapping
    public Barbeiro save(@RequestBody Barbeiro barbeiro) {
        return repository.save(barbeiro);
    }

    @GetMapping
    public List<Barbeiro> getAll() {
        List<Barbeiro> barbeiroList = repository.findAll();
        return barbeiroList;
    }

    @GetMapping("/{id}")
    public Barbeiro getById(@PathVariable long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        repository.deleteById(id);
    }

}
