package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Matricula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDTO {
    private Long identifier;
    private int numero;
    private List<Disciplina> disciplinas;
    private List<Matricula> matriculas;
    private Curso curso;
    private Instant createdOn;
    private Instant updatedOn;
}
