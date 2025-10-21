package br.com.unifor.adapters.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO extends AuditableDTO {
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private UsuarioDTO usuario;
    private CursoDTO curso;
}
