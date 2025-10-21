package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "curso")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CursoEntity extends AuditableEntity {

    private String nome;

}
