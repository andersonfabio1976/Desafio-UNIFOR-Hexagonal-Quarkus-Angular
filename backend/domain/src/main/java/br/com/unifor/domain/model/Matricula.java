package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class Matricula {
    private Long identifier;
    private Aluno aluno;
    private Curso curso;
    private StatusMatricula statusMatricula;
    private Semestre semestre;
    private Instant createdOn;
    private Instant updatedOn;
    private LocalDate dataMatricula;
}
