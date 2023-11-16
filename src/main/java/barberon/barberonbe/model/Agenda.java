package barberon.barberonbe.model;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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

    @ManyToOne
    @JoinColumn(name = "agendaBarbeiroId", nullable = false)
    private Barbeiro barbeiro;

    @Column(nullable = false)
    private String agendaDiaSemana;

    @Column(nullable = false)
    private LocalTime agendaHorarioInicio;

    @Column(nullable = false)
    private LocalTime agendaHorarioFim;;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Pausa> pausas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "agenda_status", joinColumns = @JoinColumn(name = "agenda_id"), inverseJoinColumns = @JoinColumn(name = "status_id"))
    private List<Status> status;
}
