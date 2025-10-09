package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.CursoDTO;
import br.com.unifor.adapters.repository.entity.CursoEntity;
import br.com.unifor.domain.model.Curso;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CursoMapper {
    Curso toDomain(CursoEntity entity);
    CursoEntity toEntity(Curso domain);
    CursoDTO toDTO(Curso domain);
    List<CursoDTO> toDTO(List<Curso> domain);
    Curso toDomain(CursoDTO dto);
}
