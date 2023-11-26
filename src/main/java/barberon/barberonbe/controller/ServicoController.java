package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberon.barberonbe.DTO.ServicoDTO;
import barberon.barberonbe.DTO.ServicoResponseDTO;
import barberon.barberonbe.model.Agenda;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "http://localhost:4000")

public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;

    @PostMapping("/{barbeiroId}")
    public List<Servico> addServicos(@PathVariable Long barbeiroId, @RequestBody List<ServicoDTO> servicoDTO) {
        return servicoService.addServicos(barbeiroId, servicoDTO);
    }

    @GetMapping("/{barbeiroId}")
    public ResponseEntity<List<Servico>> getServicosByBarbeiro(@PathVariable Long barbeiroId) {
        List<Servico> servicos = servicoService.getServicosByBarbeiro(barbeiroId);
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @DeleteMapping("/{servicoId}")
    public ResponseEntity<?> deleteServico(@PathVariable Long servicoId) {
        servicoService.deleteServico(servicoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{barbeiroId}/{servicoId}")
    public ResponseEntity<Servico> updateBarbeiroServico(@PathVariable Long barbeiroId, @PathVariable Long servicoId) {
        Servico servico = servicoService.updateBarbeiroServico(barbeiroId, servicoId);
        return ResponseEntity.ok().body(servico);
    }

}
