package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import br.com.unifor.domain.model.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface DisciplinaMapper extends BaseMapper<DisciplinaEntity, Disciplina, DisciplinaDTO> {
    DisciplinaEntity updateFromDto(DisciplinaDTO dto, @MappingTarget DisciplinaEntity entity);

}
