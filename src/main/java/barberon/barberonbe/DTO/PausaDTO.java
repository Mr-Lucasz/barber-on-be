package barberon.barberonbe.DTO;

import java.sql.Timestamp;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PausaDTO {
    private Long pausaId;
    private LocalTime pausaHorarioInicio;
    private LocalTime pausaHorarioFim;
    
}
