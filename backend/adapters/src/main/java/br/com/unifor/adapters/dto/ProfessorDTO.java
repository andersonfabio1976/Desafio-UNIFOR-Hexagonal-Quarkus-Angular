package br.com.unifor.adapters.dto;

public record ProfessorDTO(
        Long id,
        String nome,
        String email,
        String area
) {}
