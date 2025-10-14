package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long identifier;
    private String nome;
    private String email;
    private String area;
    private List<Disciplina> disciplinas;
    private Boolean coordenador;
    private Instant createdOn;
    private Instant updatedOn;
}
