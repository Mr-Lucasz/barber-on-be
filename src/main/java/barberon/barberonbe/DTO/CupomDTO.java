package barberon.barberonbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CupomDTO {
	 private Long id;
	 private Long quantidade;
	 private String codigo;
	 private Double porcentagem;
}
