package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@Builder
public class Disciplina {
    private Long identifier;
    private String nome;
    private Professor professor;
    private Curso curso;
    private Semestre semestre;
    private Instant createdOn;
    private Instant updatedOn;
}
