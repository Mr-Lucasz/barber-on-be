package barberon.barberonbe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "pausa")
public class Pausa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pausaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;

    @Column(nullable = false)
    private Timestamp pausaHorarioInicio;

    @Column(nullable = false)
    private Timestamp pausaHorarioFim;

    
}
