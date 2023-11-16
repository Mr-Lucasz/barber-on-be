package barberon.barberonbe.controller;

import barberon.barberonbe.model.Imagem;
import barberon.barberonbe.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/upload")
    public Imagem uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("barbeiroId") Long barbeiroId) {
        return imagemService.save(image, barbeiroId);
    }

}