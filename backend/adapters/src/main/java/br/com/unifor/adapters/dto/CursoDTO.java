package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Semestre;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO extends AuditableDTO {
    private String nome;
    private List<Disciplina> disciplinas;
    private List<Semestre> semestres;


}
