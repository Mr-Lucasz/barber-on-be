package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import barberon.barberonbe.Exception.GlobalExceptionHandler;
import barberon.barberonbe.model.Barbeiro;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import barberon.barberonbe.repository.BarbeariaRepository;
import barberon.barberonbe.repository.BarbeiroRepository;
import barberon.barberonbe.service.BarbeiroService;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
     @CrossOrigin(origins = "http://localhost:4000")
@RequestMapping("/api/barbeiros")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @PostMapping
    public Barbeiro save(@RequestBody Barbeiro barbeiro) {
        return barbeiroService.save(barbeiro);
    }

    @GetMapping
    public List<Barbeiro> findAll() {
        return barbeiroService.findAll();
    }

    @GetMapping("/{id}")
    public Barbeiro getById(@PathVariable long id) {
        return barbeiroService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        barbeiroService.deleteById(id);
    }

}
