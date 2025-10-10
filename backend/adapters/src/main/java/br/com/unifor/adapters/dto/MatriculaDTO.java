package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Semestre;
import br.com.unifor.domain.model.StatusMatricula;
import java.time.Instant;
import java.time.LocalDate;

public record MatriculaDTO(
        Long identifier,
        LocalDate dataMatricula,
        Aluno aluno,
        Semestre semestre,
        Curso curso,
        StatusMatricula statusMatricula,
        Instant createdOn,
        Instant updatedOn
) {}
