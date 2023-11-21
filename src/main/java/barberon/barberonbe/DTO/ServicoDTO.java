package barberon.barberonbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ServicoDTO {
    
    private Long servicoId;
    private String servicoNome;
    private String servicoDescricao;
    private Double servicoValor;
    private Integer servicoDuracao;
}
