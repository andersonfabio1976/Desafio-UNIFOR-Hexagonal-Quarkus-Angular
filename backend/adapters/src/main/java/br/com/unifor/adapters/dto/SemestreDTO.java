package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDTO extends AuditableDTO {
    private int numero;
    private Curso curso;
}
