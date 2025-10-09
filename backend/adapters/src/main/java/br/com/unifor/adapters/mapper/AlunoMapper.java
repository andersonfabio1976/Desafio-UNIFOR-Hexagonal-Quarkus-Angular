package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.repository.entity.AlunoEntity;
import br.com.unifor.domain.model.Aluno;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface AlunoMapper {
    Aluno toDomain(AlunoEntity entity);
    AlunoEntity toEntity(Aluno domain);
    AlunoDTO toDTO(Aluno domain);
    List<AlunoDTO> toDTO(List<Aluno> domain);
    Aluno toDomain(AlunoDTO dto);
}
