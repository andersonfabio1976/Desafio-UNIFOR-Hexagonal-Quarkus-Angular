package br.com.unifor.adapters.repository.entity;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Entity
@Table(name = "semestre")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SemestreEntity extends AuditableEntity {

    private int numero;


    @ManyToOne
    private CursoEntity curso;
}
