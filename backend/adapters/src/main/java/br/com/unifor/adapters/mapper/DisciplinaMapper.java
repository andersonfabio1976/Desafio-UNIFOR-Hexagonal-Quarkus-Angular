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

//    // Entity -> Domain usando shallow nos relacionamentos para quebrar ciclos
//    @Override
//    @Mapping(target = "curso", qualifiedByName = "cursoShallow")
//    @Mapping(target = "professor", qualifiedByName = "professorShallow")
//    @Mapping(target = "semestre", qualifiedByName = "semestreShallow")
//    Disciplina toDomainFromEntity(DisciplinaEntity entity);
//
//    // Domain -> Entity ignorando coleções de volta
//    @Override
//    @Mapping(target = "curso.disciplinas", ignore = true)
//    @Mapping(target = "curso.semestres", ignore = true)
//    @Mapping(target = "professor.disciplinas", ignore = true)
//    @Mapping(target = "semestre.disciplinas", ignore = true)
//    @Mapping(target = "semestre.matriculas", ignore = true)
//    DisciplinaEntity toEntity(Disciplina domain);
//
//    // Update in-place
//    @Override
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "curso.disciplinas", ignore = true)
//    @Mapping(target = "curso.semestres", ignore = true)
//    @Mapping(target = "professor.disciplinas", ignore = true)
//    @Mapping(target = "semestre.disciplinas", ignore = true)
//    @Mapping(target = "semestre.matriculas", ignore = true)
//    @Mapping(target = "semestre", ignore = true)
//    DisciplinaEntity toUpdateEntityMapper(Disciplina domain, @MappingTarget DisciplinaEntity entity);
//
//    // Atualização a partir de DTO (seu método auxiliar existente)
//    DisciplinaEntity updateFromDto(DisciplinaDTO dto, @MappingTarget DisciplinaEntity entity);
}