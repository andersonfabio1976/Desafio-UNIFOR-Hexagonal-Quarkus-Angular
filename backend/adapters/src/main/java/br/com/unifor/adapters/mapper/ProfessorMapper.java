package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.mapper.base.BaseMapper;
import br.com.unifor.adapters.repository.entity.AlunoEntity;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Professor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProfessorMapper extends BaseMapper<ProfessorEntity, Professor, ProfessorDTO> {
}
