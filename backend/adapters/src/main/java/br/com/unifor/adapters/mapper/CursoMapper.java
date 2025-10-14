package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.CursoDTO;
import br.com.unifor.adapters.mapper.base.BaseMapper;
import br.com.unifor.adapters.repository.entity.CursoEntity;
import br.com.unifor.domain.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface CursoMapper extends BaseMapper<CursoEntity, Curso, CursoDTO> {
    CursoEntity updateFromDto(CursoDTO dto, @MappingTarget CursoEntity entity);
}
