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
public class Curso {
    private Long identifier;
    private String nome;
    private Instant createdOn;
    private Instant updatedOn;

    @Builder.Default
    private List<Semestre> semestres = new ArrayList<>();

    @Builder.Default
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void adicionarSemestre(Semestre semestre) {
        if (semestre == null) throw new IllegalArgumentException("Semestre não pode ser nulo");
        semestres.add(semestre);
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina == null) throw new IllegalArgumentException("Disciplina não pode ser nula");
        disciplinas.add(disciplina);
    }

    public boolean removerDisciplina(Long idDisciplina) {
        return disciplinas.removeIf(d -> d.getIdentifier().equals(idDisciplina));
    }

    public boolean removerSemestre(Long idSemestre) {
        return semestres.removeIf(s -> s.getIdentifier().equals(idSemestre));
    }
}
