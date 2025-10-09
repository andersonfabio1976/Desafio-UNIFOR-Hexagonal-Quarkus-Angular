package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Professor;
import br.com.unifor.domain.model.Semestre;

import java.time.Instant;

public record DisciplinaDTO(
        Long identifier,
        String nome,
        Professor professor,
        Curso curso,
        Semestre semestre,
        Instant createdOn,
        Instant updatedOn
) {}
