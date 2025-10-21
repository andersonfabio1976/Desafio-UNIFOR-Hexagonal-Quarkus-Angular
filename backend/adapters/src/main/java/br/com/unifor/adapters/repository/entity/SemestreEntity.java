package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
