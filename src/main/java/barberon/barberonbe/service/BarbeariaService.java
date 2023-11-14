package barberon.barberonbe.service;

import barberon.barberonbe.model.Barbearia;
import barberon.barberonbe.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public List<Barbearia> getAllBarbearias() {
        return barbeariaRepository.findAll();
    }

    public Barbearia save(Barbearia barbearia) {
        return barbeariaRepository.save(barbearia);
    }

    // Adicione mais métodos conforme necessário, por exemplo, para salvar, atualizar e deletar barbearias
}