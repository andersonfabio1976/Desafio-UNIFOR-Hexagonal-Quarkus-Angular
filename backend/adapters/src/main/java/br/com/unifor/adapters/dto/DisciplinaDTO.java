package br.com.unifor.adapters.dto;

public record DisciplinaDTO(
        Long id,
        String nome,
        int cargaHoraria,
        Long cursoId,
        Long professorId
) {}
