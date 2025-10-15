package br.com.unifor.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends AuditableModel {
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Usuario usuario;

    @Builder.Default
    private List<Matricula> matriculas = new ArrayList<>();

    public void matricularCurso(Curso curso) {
        if (curso == null) throw new IllegalArgumentException("Curso não pode ser nulo");
        matriculas.add(Matricula.builder()
                .aluno(this)
                .curso(curso)
                .statusMatricula(StatusMatricula.ATIVA)
                .build());
    }

    public boolean isMaiorDeIdade() {
        return dataNascimento.plusYears(18).isBefore(LocalDate.now());
    }
}
