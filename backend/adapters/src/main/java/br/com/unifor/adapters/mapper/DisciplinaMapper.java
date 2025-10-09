package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import br.com.unifor.domain.model.Disciplina;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface DisciplinaMapper {
    Disciplina toDomain(DisciplinaEntity entity);
    DisciplinaEntity toEntity(Disciplina domain);
    DisciplinaDTO toDTO(Disciplina domain);
    List<DisciplinaDTO> toDTO(List<Disciplina> domain);
    Disciplina toDomain(DisciplinaDTO dto);
}
