package br.com.unifor.adapters.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO extends AuditableDTO {
    private String nome;

}
