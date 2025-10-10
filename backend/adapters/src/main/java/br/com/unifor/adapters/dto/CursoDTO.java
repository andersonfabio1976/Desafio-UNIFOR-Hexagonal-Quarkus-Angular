package br.com.unifor.adapters.dto;

import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Semestre;
import java.time.Instant;
import java.util.List;

public record CursoDTO(
        Long identifier,
        String nome,
        List<Disciplina> disciplinas,
        List<Semestre> semestres,
        Instant createdOn,
        Instant updatedOn

) {}
