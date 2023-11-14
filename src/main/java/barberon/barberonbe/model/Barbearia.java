package barberon.barberonbe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "barbearia", schema = "public")
public class Barbearia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long barbeariaId;

    @Column(nullable = false)
    private String barbeariaNome;
    @Column(nullable = false)
    private String barbeariaDescricao;
    @Column(nullable = false)
    private String barbeariaCNPJ;
    @Column(nullable = false)
    private String barbeariaEnderecoCEP;
    @Column(nullable = false)
    private String barbeariaEnderecoCidade;
    @Column(nullable = false)
    private String barbeariaEnderecoRua;
    @Column(nullable = false)
    private String barbeariaEnderecoBairro;
    @Column(nullable = false)
    private String barbeariaEnderecoNumero;
    @Column(nullable = false)
    private Double barbeariaMediaAvaliacao;

    @OneToMany(mappedBy = "barbearia")
    private List<Barbeiro> barbeiros;
    @OneToMany(mappedBy = "barbearia")
    private List<Imagem> imagens;
}