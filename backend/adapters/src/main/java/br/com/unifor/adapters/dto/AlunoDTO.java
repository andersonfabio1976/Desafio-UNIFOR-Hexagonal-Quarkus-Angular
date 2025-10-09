package br.com.unifor.adapters.dto;

import br.com.unifor.adapters.repository.entity.MatriculaEntity;
import br.com.unifor.domain.model.Matricula;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record AlunoDTO (
        Long identifier,
        String nome,
        String email,
        LocalDate dataNascimento,
        List<Matricula> matriculas,
        Instant createdOn,
        Instant updatedOn
) {}
