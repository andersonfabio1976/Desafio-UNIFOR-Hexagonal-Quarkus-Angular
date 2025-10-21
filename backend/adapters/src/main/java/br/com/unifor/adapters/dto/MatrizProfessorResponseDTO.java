package br.com.unifor.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MatrizProfessorResponseDTO {
    private List<MatrizItemDTO> itens;
}