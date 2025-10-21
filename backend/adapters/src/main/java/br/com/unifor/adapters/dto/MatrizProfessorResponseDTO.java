package br.com.unifor.adapters.dto;

import java.util.List;

public record MatrizProfessorResponseDTO(
        List<MatrizItemDTO> itens
) {}