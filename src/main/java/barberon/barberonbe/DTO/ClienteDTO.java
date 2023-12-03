package barberon.barberonbe.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDTO {
	private Long id;
	private String nome;
	private Date dataNascimento;
	private String cpf;
	private String telefone;
	private String email;
	private String senha;
	private Long imagemId;
}
