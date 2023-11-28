package barberon.barberonbe.DTO;

import java.security.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RelatorioDTO {
    private String nomeBarbeiro;
    private String nomeServico;
    private Timestamp horario;
    private double valor;

}
