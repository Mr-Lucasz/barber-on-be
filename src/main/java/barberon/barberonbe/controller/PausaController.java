
package barberon.barberonbe.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.service.PausaService;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/pausas")

public class PausaController {
    private final PausaService pausaService;

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

    // get para buscar pausa por id
    @GetMapping("/{pausaId}")
    public Pausa getPausaById(@PathVariable Long pausaId) {
        return pausaService.getPausaById(pausaId);
    }

    @DeleteMapping("/{pausaId}")
    public void deletePausaById(@PathVariable Long pausaId) {
        pausaService.deletePausaById(pausaId);
    }

    // salve list by agendaId
    @PostMapping("/list")
    public List<Pausa> savePausaList(@RequestBody List<Pausa> pausaList) {
        return pausaService.savePausaList(pausaList);
    }

}