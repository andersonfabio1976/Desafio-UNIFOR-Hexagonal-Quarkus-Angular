package br.com.unifor.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Semestre extends AuditableModel {
    private int numero;

    @Builder.Default
    private List<Disciplina> disciplinas = new ArrayList<>();

    @Builder.Default
    private List<Matricula> matriculas = new ArrayList<>();

    private Curso curso;

    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina == null) throw new IllegalArgumentException("Disciplina não pode ser nula");
        disciplinas.add(disciplina);
    }
}
