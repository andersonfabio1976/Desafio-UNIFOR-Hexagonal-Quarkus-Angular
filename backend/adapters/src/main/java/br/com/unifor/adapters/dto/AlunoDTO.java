package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Matricula;
import br.com.unifor.domain.model.Usuario;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO extends AuditableDTO {
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private List<Matricula> matriculas;
    private UsuarioDTO usuario;
}
