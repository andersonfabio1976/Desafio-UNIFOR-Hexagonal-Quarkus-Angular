package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.unifor.domain.model.StatusMatricula;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "matricula")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MatriculaEntity extends AuditableEntity {

    private LocalDate dataMatricula;

    @ManyToOne
    @JoinColumn(name = "aluno_identifier")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "curso_identifier")
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semestre_identifier")
    private SemestreEntity semestre;

    @Enumerated(EnumType.STRING)
    private StatusMatricula statusMatricula;
}
