package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class Professor {
    private Long identifier;
    private String nome;
    private String email;
    private String area;
    private List<Disciplina> disciplinas;
    private Instant createdOn;
    private Instant updatedOn;
}
