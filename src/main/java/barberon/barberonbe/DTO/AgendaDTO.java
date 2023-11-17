package barberon.barberonbe.DTO;

import java.time.LocalTime;
import java.util.List;

import barberon.barberonbe.model.Pausa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AgendaDTO {
    private Long agendaId;
    private String agendaDiaSemana;
    private LocalTime agendaHorarioInicio;
    private LocalTime agendaHorarioFim;
    private List<Pausa> pausas;
    private String statusNome; 
}