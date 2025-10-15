package br.com.unifor.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends AuditableModel {
    private String nome;
    private Boolean coordenador;
    private String email;
    private String area;
    private List<Disciplina> disciplinas;
    private Usuario usuario;
}
