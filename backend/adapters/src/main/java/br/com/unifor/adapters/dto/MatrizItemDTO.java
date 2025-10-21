package br.com.unifor.adapters.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MatrizItemDTO {
    private Integer periodo;
    private String disciplina;
    private Integer cargaHoraria;
    private String professor;
}