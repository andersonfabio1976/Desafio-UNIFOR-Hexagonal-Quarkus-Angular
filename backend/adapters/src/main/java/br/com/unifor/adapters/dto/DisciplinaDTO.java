package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Professor;
import br.com.unifor.domain.model.Semestre;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DisciplinaDTO extends AuditableDTO {
    private String nome;
    private Professor professor;
    private Curso curso;
    private Semestre semestre;
    private String cargaHoraria;
}
