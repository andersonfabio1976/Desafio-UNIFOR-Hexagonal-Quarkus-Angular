package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.repository.entity.SemestreEntity;
import br.com.unifor.domain.model.Semestre;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", uses = { CursoMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SemestreMapper extends BaseMapper<SemestreEntity, Semestre, SemestreDTO> {

}