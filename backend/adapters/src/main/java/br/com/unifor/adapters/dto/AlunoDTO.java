package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Matricula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private Long identifier;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private List<Matricula> matriculas;
    private Instant createdOn;
    private Instant updatedOn;
}
