package barberon.barberonbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import barberon.barberonbe.DTO.BarbeiroDTO;
import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.service.BarbeiroService;

import java.util.List;

@RestController
@RequestMapping("/api/barbeiros")
@CrossOrigin(origins = "http://localhost:4000")

public class BarbeiroController {
    @Autowired
    private BarbeiroService barbeiroService;

    @PostMapping
    public BarbeiroDTO save(@RequestBody Barbeiro barbeiro) {
        return barbeiroService.save(barbeiro);
    }

    @GetMapping
    public List<BarbeiroDTO> findAll() {
        return barbeiroService.findAllBarbeirosWithAgendasAndServicos();
    }
    

    @GetMapping("/{id}")
    public Barbeiro getById(@PathVariable long id) {
        return barbeiroService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        barbeiroService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public BarbeiroDTO updateBarbeiro(@PathVariable long id, @RequestBody BarbeiroDTO barbeiroDTO) {
        return barbeiroService.updateBarbeiro(id, barbeiroDTO);
    }



}