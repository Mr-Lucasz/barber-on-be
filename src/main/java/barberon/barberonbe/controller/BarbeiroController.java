package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.repository.BarbeiroRepository;
import java.util.List;

@RestController
@RequestMapping("/barber")
public class BarbeiroController {

    @Autowired
    private BarbeiroRepository repository;

    @GetMapping
    public List<Barbeiro> getAll() {
        List<Barbeiro> barbeiroList = repository.findAll();
        return barbeiroList;
    }
}
