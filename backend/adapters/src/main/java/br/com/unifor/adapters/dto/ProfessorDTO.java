package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Disciplina;

import java.time.Instant;
import java.util.List;

public record ProfessorDTO(
        Long identifier,
        String nome,
        String email,
        String area,
        List<Disciplina> disciplinas,
        Instant createdOn,
        Instant updatedOn
        ) {}
