package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.mapper.base.BaseMapper;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import br.com.unifor.domain.model.Disciplina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface DisciplinaMapper extends BaseMapper<DisciplinaEntity, Disciplina, DisciplinaDTO> {

}
