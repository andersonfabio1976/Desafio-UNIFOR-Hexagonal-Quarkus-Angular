package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Semestre;
import br.com.unifor.domain.model.StatusMatricula;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO extends AuditableDTO {
    private LocalDate dataMatricula;
    private Aluno aluno;
    private Semestre semestre;
    private Curso curso;
    private StatusMatricula statusMatricula;
}
