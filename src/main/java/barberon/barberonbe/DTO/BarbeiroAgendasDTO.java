package barberon.barberonbe.DTO;

import java.util.List;

import barberon.barberonbe.model.Agenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BarbeiroAgendasDTO {

    private Long barbeiroId;
    private List<AgendaDTO> agendas;

    public void setAgendas(List<AgendaDTO> agendas) {
        this.agendas = agendas;
    }

}
