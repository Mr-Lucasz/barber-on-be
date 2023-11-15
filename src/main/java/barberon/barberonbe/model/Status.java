package barberon.barberonbe.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "status")

public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusId;

    @Column(nullable = false)
    private String statusNome;

    // getters and setters
}