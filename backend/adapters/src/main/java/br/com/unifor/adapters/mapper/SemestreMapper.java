package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.mapper.base.BaseMapper;
import br.com.unifor.adapters.repository.entity.SemestreEntity;
import br.com.unifor.domain.model.Semestre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface SemestreMapper extends BaseMapper<SemestreEntity, Semestre, SemestreDTO> {

}
