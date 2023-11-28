package barberon.barberonbe.DTO;

import java.sql.Date;

import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Cliente;
import barberon.barberonbe.model.Servico;
import barberon.barberonbe.model.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AgendamentoDTO {
    private Long barbeiroId;
    private String barbeiroNome;
    private Date horaInicio;
    private Date horaFim;
    private ServicoDTO servico;
    private ClienteDTO cliente;
    private StatusDTO status;
}
