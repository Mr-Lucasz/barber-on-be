package barberon.barberonbe.controller;

import barberon.barberonbe.model.Imagem;
import barberon.barberonbe.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/imagens")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @GetMapping
    public List<Imagem> getAllImagens() {
        return imagemService.getAllImagens();
    }

    // Adicione mais endpoints conforme necessário, por exemplo, para criar, atualizar e deletar imagens

    
}