package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoEntity extends AuditableEntity {

    private String nome;
    private String email;
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "usuario_identifier")
    UsuarioEntity usuario;

    @OneToMany(mappedBy = "aluno")
    private List<MatriculaEntity> matriculas;
}
