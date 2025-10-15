package br.com.unifor.adapters.dto;

import java.util.List;

public record MatrizAlunoResponseDTO(
        Long cursoId,
        List<MatrizItemDTO> itens
) {}