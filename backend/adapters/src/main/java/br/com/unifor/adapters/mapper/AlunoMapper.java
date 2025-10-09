package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.mapper.base.BaseMapper;
import br.com.unifor.adapters.repository.entity.AlunoEntity;
import br.com.unifor.domain.model.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AlunoMapper extends BaseMapper<AlunoEntity, Aluno, AlunoDTO> {
}
