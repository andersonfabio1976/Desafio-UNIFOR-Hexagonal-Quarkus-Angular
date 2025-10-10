package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class Semestre {
    private Long identifier;
    private int numero;
    private Instant createdOn;
    private Instant updatedOn;

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
