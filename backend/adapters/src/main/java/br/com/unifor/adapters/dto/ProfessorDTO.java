package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Usuario;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO extends AuditableDTO {
    private String nome;
    private String email;
    private String area;
    private List<Disciplina> disciplinas;
    private Boolean coordenador;
    private UsuarioDTO usuario;
}
