package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "professor")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProfessorEntity extends AuditableEntity {

    private String nome;
    private String email;
    private String area;
    private Boolean coordenador;

    @ManyToOne
    @JoinColumn(name = "usuario_identifier")
    private UsuarioEntity usuario;

}
