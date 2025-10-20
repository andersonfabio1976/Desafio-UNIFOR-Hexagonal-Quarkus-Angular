package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.CursoDTO;
import br.com.unifor.adapters.repository.entity.CursoEntity;
import br.com.unifor.domain.model.Curso;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CursoMapper extends BaseMapper<CursoEntity, Curso, CursoDTO> {
}