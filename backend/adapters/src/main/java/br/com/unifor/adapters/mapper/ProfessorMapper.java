package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.domain.model.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorMapper extends BaseMapper<ProfessorEntity, Professor, ProfessorDTO> {

}