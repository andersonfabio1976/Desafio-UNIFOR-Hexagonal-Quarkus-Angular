package br.com.unifor.adapters.repository.entity;

import br.com.unifor.domain.model.Semestre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CursoEntity extends AuditableEntity {

    private String nome;

    @OneToMany(mappedBy = "curso")
    private List<DisciplinaEntity> disciplinas;

    @OneToMany(mappedBy = "curso")
    private List<SemestreEntity> semestres;
}
