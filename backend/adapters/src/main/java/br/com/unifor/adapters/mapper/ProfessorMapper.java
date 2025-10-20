package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.domain.model.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorMapper extends BaseMapper<ProfessorEntity, Professor, ProfessorDTO> {

//    @Override
//    @Mapping(target = "disciplinas", ignore = true)
//    Professor toDomainFromEntity(ProfessorEntity entity);
//
//    @Override
//    @Mapping(target = "disciplinas", ignore = true)
//    ProfessorEntity toEntity(Professor domain);
//
//    @Override
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "disciplinas", ignore = true)
//    ProfessorEntity toUpdateEntityMapper(Professor domain, @MappingTarget ProfessorEntity entity);
//
//    // Shallow para evitar ciclo Disciplina -> Professor -> Disciplinas -> ...
//    @Named("professorShallow")
//    @Mapping(target = "disciplinas", ignore = true)
//    Professor toShallowDomain(ProfessorEntity entity);
//
//    @Override
//    ProfessorDTO toDTO(Professor domain);
//
//    @Override
//    List<ProfessorDTO> toListDTO(List<Professor> domainList);
//
//    @Override
//    @Mapping(target = "disciplinas", ignore = true)
//    Professor toDomainFromDTO(ProfessorDTO dto);
}