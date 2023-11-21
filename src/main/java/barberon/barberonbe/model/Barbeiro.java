package barberon.barberonbe.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import barberon.barberonbe.DTO.AgendaDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@EqualsAndHashCode(of = "id")

public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barbeariaId", nullable = false)
    private Barbearia barbearia;

    @Column(nullable = false)
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = true)
    private Double mediaAvaliacao;

    @OneToMany(mappedBy = "barbeiro", fetch = FetchType.EAGER)
    private List<Agenda> agendas = new ArrayList<>();
    

    @OneToMany(mappedBy = "barbeiro", fetch = FetchType.EAGER)
    private List<Servico> servicos = new ArrayList<>();

    @OneToOne(mappedBy = "barbeiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Imagem imagem;
    

}