package barberon.barberonbe.controller;

import barberon.barberonbe.model.Barbearia;
import barberon.barberonbe.service.BarbeariaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    private static final Logger logger = LoggerFactory.getLogger(BarbeariaController.class);

    @GetMapping
    public List<Barbearia> getAllBarbearias() {
        return barbeariaService.getAllBarbearias();
    }

    @PostMapping
    public Barbearia createBarbearia(@RequestBody Barbearia barbearia) throws Exception {
        try {
            return barbeariaService.save(barbearia);
        } catch (Exception e) {
            throw new Exception("Erro ao criar barbearia", e);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Erro ao criar barbearia", e);
        return new ResponseEntity<>("Erro ao criar barbearia: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}