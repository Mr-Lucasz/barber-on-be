package barberon.barberonbe.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "imagem", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imagemId;

    private String imagemCaminho;

    @ManyToOne
    @JoinColumn(name = "barbeariaId")
    private Barbearia barbearia;

    @ManyToOne
    @JoinColumn(name = "barbeiroId")
    private Barbeiro barbeiro;
}