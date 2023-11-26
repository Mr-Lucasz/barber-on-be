package barberon.barberonbe.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cupom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cupom {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column(nullable = false)
	    private Long quantidade;

	    @Column(nullable = false)
	    @NotBlank(message = "Codigo é obrigatório")
	    private String codigo;

	    @Column(nullable = false)
	    private Double porcentagem;

}
