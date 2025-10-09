package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Disciplina {
    private Long identifier;
    private String nome;
    private String codigo;
    private Professor professor;
}
