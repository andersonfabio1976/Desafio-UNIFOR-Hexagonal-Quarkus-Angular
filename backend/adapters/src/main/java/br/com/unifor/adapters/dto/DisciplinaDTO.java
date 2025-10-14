package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Professor;
import br.com.unifor.domain.model.Semestre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DisciplinaDTO {
    private Long identifier;
    private String nome;
    private Professor professor;
    private Curso curso;
    private Semestre semestre;
    private Instant createdOn;
    private Instant updatedOn;
}
