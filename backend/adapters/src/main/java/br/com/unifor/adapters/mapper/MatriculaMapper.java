package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.repository.entity.MatriculaEntity;
import br.com.unifor.domain.model.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface MatriculaMapper extends BaseMapper<MatriculaEntity, Matricula, MatriculaDTO> {
    MatriculaEntity updateFromDto(MatriculaDTO dto, @MappingTarget MatriculaEntity entity);
}
