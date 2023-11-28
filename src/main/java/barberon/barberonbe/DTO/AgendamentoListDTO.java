package barberon.barberonbe.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoListDTO {
    private Long id;
    private Long barbeiroId;
    private String barbeiroNome;
    private List<AgendamentoDTO> agendamentos;
}
