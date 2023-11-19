package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "http://localhost:4000")

public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public Servico save(@RequestBody Servico servico) {
        return servicoService.save(servico);
    }

    @GetMapping
    public List<Servico> findAll() {
        return servicoService.findAll();
    }

    @GetMapping("/{id}")
    public Servico getById(@PathVariable long id) {
        return servicoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        servicoService.deleteById(id);
    }
}