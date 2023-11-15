
package barberon.barberonbe.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.service.PausaService;

@RestController
@RequestMapping("/api/pausas")
public class PausaController {
    private final PausaService pausaService;

    @Autowired
    public PausaController(PausaService pausaService) {
        this.pausaService = pausaService;
    }

    @PostMapping
    public Pausa createPausa(@RequestBody Pausa pausa) {
        return pausaService.savePausa(pausa);
    }

    @GetMapping
    public List<Pausa> getAllPausas() {
        return pausaService.getAllPausas();
    }

    // Add more endpoints as needed
}