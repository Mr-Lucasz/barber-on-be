package barberon.barberonbe.DTO;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BarbeiroDTO {

    private Long id;
    private Long barbeariaId;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private Double mediaAvaliacao;
    private List<AgendaDTO> agendas;
    private List<ServicoDTO> servicos;
    private Long imagemId;

}
