package barberon.barberonbe.controller;

import barberon.barberonbe.model.Barbearia;
import barberon.barberonbe.service.BarbeariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/barbearias")
public class BarbeariaController {

    @Autowired
    private BarbeariaService barbeariaService;

    @GetMapping
    public List<Barbearia> getAllBarbearias() {
        return barbeariaService.getAllBarbearias();
    }

    @PostMapping
    public Barbearia createBarbearia(@RequestBody Barbearia barbearia) {
        return barbeariaService.save(barbearia);
    }

}