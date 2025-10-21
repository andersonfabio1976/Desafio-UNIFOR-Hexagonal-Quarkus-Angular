package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import br.com.unifor.domain.model.Disciplina;
import org.mapstruct.*;

@Mapper(
        componentModel = "cdi",
        uses = { CursoMapper.class, ProfessorMapper.class, SemestreMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DisciplinaMapper extends BaseMapper<DisciplinaEntity, Disciplina, DisciplinaDTO> {

}