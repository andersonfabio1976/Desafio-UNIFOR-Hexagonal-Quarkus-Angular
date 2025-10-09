package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class Semestre {
    private Long identifier;
    private int numero;

    @Builder.Default
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina == null) throw new IllegalArgumentException("Disciplina não pode ser nula");
        disciplinas.add(disciplina);
    }
}
