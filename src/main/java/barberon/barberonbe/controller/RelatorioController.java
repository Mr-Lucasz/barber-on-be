package barberon.barberonbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barberon.barberonbe.DTO.RelatorioDTO;
import barberon.barberonbe.service.RelatorioService;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "http://localhost:4000")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/gerarRelatorio")
    public List<RelatorioDTO> gerarRelatorio() {
        return relatorioService.buscarRelatorio();
    }
}
