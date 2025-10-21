package br.com.unifor.adapters.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO extends AuditableDTO {
    private String nome;
    private String email;
    private String area;
    private Boolean coordenador;
    private UsuarioDTO usuario;
}
