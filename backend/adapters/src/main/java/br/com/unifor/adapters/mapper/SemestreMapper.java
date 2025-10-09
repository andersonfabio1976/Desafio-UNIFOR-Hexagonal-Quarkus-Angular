package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.repository.entity.SemestreEntity;
import br.com.unifor.domain.model.Semestre;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface SemestreMapper {
    Semestre toDomain(SemestreEntity entity);
    SemestreEntity toEntity(Semestre domain);
    SemestreDTO toDTO(Semestre domain);
    List<SemestreDTO> toDTO(List<Semestre> domain);
    Semestre toDomain(SemestreDTO dto);
}
