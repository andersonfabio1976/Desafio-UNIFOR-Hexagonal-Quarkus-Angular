package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.repository.entity.SemestreEntity;
import br.com.unifor.domain.model.Semestre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface SemestreMapper extends BaseMapper<SemestreEntity, Semestre, SemestreDTO> {
    SemestreEntity updateFromDto(SemestreDTO dto, @MappingTarget SemestreEntity entity);
}
