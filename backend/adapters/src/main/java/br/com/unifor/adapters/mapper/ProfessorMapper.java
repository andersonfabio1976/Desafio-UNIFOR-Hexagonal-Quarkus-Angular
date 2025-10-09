package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.domain.model.Professor;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProfessorMapper {
    Professor toDomain(ProfessorEntity entity);
    ProfessorEntity toEntity(Professor domain);
    ProfessorDTO toDTO(Professor domain);
    List<ProfessorDTO> toDTO(List<Professor> domain);
    Professor toDomain(ProfessorDTO dto);
}
