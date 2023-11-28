package barberon.barberonbe.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barbeiro", schema = "public")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
public class Barbeiro extends Usuario {

    @ManyToOne
    @JoinColumn(name = "barbeariaId", nullable = false)
    private Barbearia barbearia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = true)
    private Double mediaAvaliacao;

    @OneToMany(mappedBy = "barbeiro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Agenda> agendas = new ArrayList<>();

    @OneToMany(mappedBy = "barbeiro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Servico> servicos = new ArrayList<>();

    @OneToOne(mappedBy = "barbeiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Imagem imagem;

}