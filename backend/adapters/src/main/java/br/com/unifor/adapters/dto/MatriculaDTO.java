package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Semestre;
import br.com.unifor.domain.model.StatusMatricula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO {
    private Long identifier;
    private LocalDate dataMatricula;
    private Aluno aluno;
    private Semestre semestre;
    private Curso curso;
    private StatusMatricula statusMatricula;
    private Instant createdOn;
    private Instant updatedOn;
}
