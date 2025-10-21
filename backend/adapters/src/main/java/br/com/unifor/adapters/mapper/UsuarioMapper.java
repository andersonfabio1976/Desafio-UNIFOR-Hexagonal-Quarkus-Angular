package br.com.unifor.adapters.mapper;

import br.com.unifor.adapters.dto.UsuarioDTO;
import br.com.unifor.adapters.repository.entity.UsuarioEntity;
import br.com.unifor.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface UsuarioMapper extends BaseMapper<UsuarioEntity, Usuario, UsuarioDTO> {

    @Mapping(target = "password", ignore = true)
    void updateFromDto(UsuarioDTO dto, @MappingTarget UsuarioEntity entity);
}
