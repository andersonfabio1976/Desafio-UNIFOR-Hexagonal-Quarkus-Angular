package br.com.unifor.adapters.dto;

import java.time.LocalDate;

public record MatriculaDTO(
        Long id,
        Long alunoId,
        Long cursoId,
        LocalDate dataMatricula
) {}
