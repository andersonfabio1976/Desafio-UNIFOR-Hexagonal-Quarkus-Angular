package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import br.com.unifor.domain.model.StatusMatricula;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
@Getter
@Setter
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataMatricula;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semestre_id")
    private SemestreEntity semestre;

    @Enumerated(EnumType.STRING)
    private StatusMatricula status;
}
