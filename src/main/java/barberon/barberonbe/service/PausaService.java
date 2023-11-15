package barberon.barberonbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.repository.PausaRepository;

@Service
public class PausaService {
    private final PausaRepository pausaRepository;

    @Autowired
    public PausaService(PausaRepository pausaRepository) {
        this.pausaRepository = pausaRepository;
    }

    public Pausa savePausa(Pausa pausa) {
        return pausaRepository.save(pausa);
    }

    public List<Pausa> getAllPausas() {
        return pausaRepository.findAll();
    }

    public Pausa getPausaById(Long id) {
        return pausaRepository.findById(id).orElse(null);
    }

    public void deletePausaById(Long id) {
        pausaRepository.deleteById(id);
    }

    public Pausa updatePausa(Pausa pausa) {
        Pausa existingPausa = pausaRepository.findById(pausa.getPausaId()).orElse(null);
        existingPausa.setPausaId(pausa.getPausaId());
        existingPausa.setPausaHorarioInicio(pausa.getPausaHorarioInicio());
        existingPausa.setPausaHorarioFim(pausa.getPausaHorarioFim());
        return pausaRepository.save(existingPausa);
    }

}