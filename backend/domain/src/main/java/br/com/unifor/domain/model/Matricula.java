package br.com.unifor.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Matricula extends AuditableModel {
    private Aluno aluno;
    private Curso curso;
    private StatusMatricula statusMatricula;
    private Semestre semestre;
    private LocalDate dataMatricula;
}
