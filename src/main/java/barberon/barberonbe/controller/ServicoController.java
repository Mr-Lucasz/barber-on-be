package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberon.barberonbe.DTO.ServicoDTO;
import barberon.barberonbe.DTO.ServicoResponseDTO;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "http://localhost:4000")

public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @PostMapping("/barbeiro/{barbeiroId}")
    public List<Servico> addServicos(@PathVariable Long barbeiroId, @RequestBody List<ServicoDTO> servicoDTOs) {
        return servicoService.addServicos(barbeiroId, servicoDTOs);
    }

  @GetMapping
    public List<ServicoResponseDTO> findAll() {
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

    @PutMapping("/{id}")
    public void uptadeServiceById(@PathVariable long id, @RequestBody Servico servico) {
        servicoService.updateServiceById(id, servico);
    }

}