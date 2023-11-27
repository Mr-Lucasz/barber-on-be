package barberon.barberonbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barberon.barberonbe.DTO.AvaliacaoDTO;
import barberon.barberonbe.model.Avaliacao;
import barberon.barberonbe.service.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "http://localhost:4000")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avalicaoService;

    @PostMapping
    public AvaliacaoDTO save(@RequestBody Avaliacao avaliacao) {
        return avalicaoService.save(avaliacao);
    }

    @GetMapping
    public List<Avaliacao> findAll() {
        return avalicaoService.findAll();
    }

    @GetMapping("/{id}")
    public Avaliacao getById(@PathVariable long id) {
        return avalicaoService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> update(@PathVariable long id, @RequestBody Avaliacao avaliacao) {
        AvaliacaoDTO updatedAvaliacao = avalicaoService.update(id, avaliacao);
        return new ResponseEntity<>(updatedAvaliacao, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        avalicaoService.deleteById(id);
    }
}
