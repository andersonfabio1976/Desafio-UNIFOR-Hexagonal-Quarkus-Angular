package br.com.unifor.adapters.dto;

import java.time.LocalDate;

public record AlunoDTO(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {}
