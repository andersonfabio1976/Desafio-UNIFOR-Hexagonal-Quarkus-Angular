package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "disciplina")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DisciplinaEntity extends AuditableEntity {

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_identifier")
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_identifier")
    private ProfessorEntity professor;

    @ManyToOne
    @JoinColumn(name = "semestre_identifier")
    private SemestreEntity semestre;
}
