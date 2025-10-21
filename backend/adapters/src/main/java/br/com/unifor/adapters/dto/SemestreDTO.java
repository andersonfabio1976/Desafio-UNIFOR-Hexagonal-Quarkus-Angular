package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDTO extends AuditableDTO {
    private int numero;
    private Curso curso;
}
