package br.com.unifor.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Professor {
    private Long identifier;
    private String nome;
    private String email;
}
