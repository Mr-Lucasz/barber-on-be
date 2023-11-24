package barberon.barberonbe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long servicoId;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;

    @OneToOne
    @JoinColumn(name = "imagem_id", nullable = true)
    private Imagem imagem;
  
    @Column(nullable = false)
    private String servicoTitulo;

    @Column(nullable = true, length = 1000)
    private String servicoDescricao;

    @Column(nullable = false)
    private int servicoTempoHora;

    @Column(nullable = false)
    private int servicoTempoMinuto;

    @Column(nullable = false)
    private double servicoValor;

}