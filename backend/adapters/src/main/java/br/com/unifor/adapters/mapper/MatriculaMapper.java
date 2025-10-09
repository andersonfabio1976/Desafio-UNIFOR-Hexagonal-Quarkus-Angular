package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.repository.entity.MatriculaEntity;
import br.com.unifor.domain.model.Matricula;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface MatriculaMapper  {
    Matricula toDomain(MatriculaEntity entity);
    MatriculaEntity toEntity(Matricula domain);
    MatriculaDTO toDTO(Matricula domain);
    List<MatriculaDTO> toDTO(List<Matricula> domain);
    Matricula toDomain(MatriculaDTO dto);
}
