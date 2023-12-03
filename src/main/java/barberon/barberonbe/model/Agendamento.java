package barberon.barberonbe.model;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento")
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "barbeiro_id", nullable = false)
	private Barbeiro barbeiro;

	@Column(nullable = false)
	private Date horaInicio;

	@Column(nullable = false)
	private Date horaFim;

	@ManyToMany
    @JoinTable(
      name = "agendamento_servico",
      joinColumns = @JoinColumn(name = "agendamento_id"),
      inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private List<Servico> servicos;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;
}