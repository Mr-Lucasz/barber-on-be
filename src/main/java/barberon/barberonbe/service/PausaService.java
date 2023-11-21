package barberon.barberonbe.service;

import java.util.List;
import org.springframework.stereotype.Service;

import barberon.barberonbe.DTO.PausaDTO;
import barberon.barberonbe.model.Pausa;
import barberon.barberonbe.repository.PausaRepository;

@Service
public class PausaService {
    private final PausaRepository pausaRepository;

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

    public Pausa updatePausa(PausaDTO pausaDTO) {
        Pausa existingPausa = pausaRepository.findById(pausaDTO.getPausaId()).orElse(null);
    
        if (existingPausa == null) {
            existingPausa = new Pausa();
            // Define o id da nova Pausa como o id fornecido
            existingPausa.setPausaId(pausaDTO.getPausaId());
        }
    
        existingPausa.setPausaHorarioInicio(pausaDTO.getPausaHorarioInicio());
        existingPausa.setPausaHorarioFim(pausaDTO.getPausaHorarioFim());
    
        return pausaRepository.save(existingPausa);
    }

}