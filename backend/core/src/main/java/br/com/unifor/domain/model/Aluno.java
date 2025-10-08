package br.com.unifor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Aluno {
    private final Long identifier;
    private final String nome;
    private final String matricula;
}
