package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Entity
@Table(name = "professor")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProfessorEntity extends BaseEntity {

    private String nome;
    private String email;
    private String area;
    private Boolean coordenador;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisciplinaEntity> disciplinas;
}
