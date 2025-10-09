package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Matricula {
    private Long identifier;
    private Aluno aluno;
    private Curso curso;
    private StatusMatricula status;
}
