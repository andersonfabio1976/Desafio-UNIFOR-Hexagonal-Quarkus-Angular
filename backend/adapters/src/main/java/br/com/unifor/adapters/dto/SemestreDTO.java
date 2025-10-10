package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Matricula;

import java.time.Instant;
import java.util.List;

public record SemestreDTO(
        Long identifier,
        int numero,
        List<Disciplina> disciplinas,
        List<Matricula> matriculas,
        Curso curso,
        Instant createdOn,
        Instant updatedOn
) {}
