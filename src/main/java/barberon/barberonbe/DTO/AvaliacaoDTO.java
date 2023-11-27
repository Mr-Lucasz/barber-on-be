package barberon.barberonbe.DTO;

import barberon.barberonbe.model.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private int avaliacaoNota;
    private Agendamento agendamento;
}
