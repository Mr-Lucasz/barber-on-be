package barberon.barberonbe.service;

import barberon.barberonbe.model.Imagem;
import barberon.barberonbe.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    public List<Imagem> getAllImagens() {
        return imagemRepository.findAll();
    }

    // Adicione mais métodos conforme necessário, por exemplo, para salvar, atualizar e deletar imagens
}