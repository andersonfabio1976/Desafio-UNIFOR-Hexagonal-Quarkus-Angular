package br.com.unifor.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatrizItemDTO {
    private Integer periodo;
    private String disciplina;
    private Integer cargaHoraria;
    private String professor;
}