package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.mapper.base.BaseMapper;
import br.com.unifor.adapters.repository.entity.MatriculaEntity;
import br.com.unifor.domain.model.Matricula;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface MatriculaMapper extends BaseMapper<MatriculaEntity, Matricula, MatriculaDTO> {
}
