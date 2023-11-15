package barberon.barberonbe.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long agendaId;

    @Column(nullable = false)
    private Integer agendaBarbeiroId;

    @Column(nullable = false)
    private Integer agendaDiaSemana;

    @Column(nullable = false)
    private Timestamp agendaHorarioInicio;

    @Column(nullable = false)
    private Timestamp agendaHorarioFim;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pausa> pausas;

    

}