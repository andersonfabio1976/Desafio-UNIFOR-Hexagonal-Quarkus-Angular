package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Semestre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Long identifier;
    private String nome;
    private List<Disciplina> disciplinas;
    private List<Semestre> semestres;
    private Instant createdOn;
    private Instant updatedOn;

}
