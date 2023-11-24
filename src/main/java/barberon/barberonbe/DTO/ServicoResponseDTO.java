package barberon.barberonbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ServicoResponseDTO {
    private Long servicoId;
    private Long barbeiroId;
    private Object imagem;
    private String servicoTitulo;
    private String servicoDescricao;
    private int servicoTempoHora;
    private int servicoTempoMinuto;
    private double servicoValor;
}