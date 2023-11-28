package barberon.barberonbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import barberon.barberonbe.DTO.RelatorioDTO;
import barberon.barberonbe.repository.AgendamentoRepository;

public class RelatorioService {
    @Autowired
    private AgendamentoRepository repository;

    public List<RelatorioDTO> buscarRelatorio() {
        return repository.findAllRelatorio();
    }
}
