package br.com.unifor.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina extends AuditableModel {
    private String nome;
    private Professor professor;
    private Curso curso;
    private Semestre semestre;
    private String cargaHoraria;
}
