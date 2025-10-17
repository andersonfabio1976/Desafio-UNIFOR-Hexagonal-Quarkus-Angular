package br.com.unifor.adapters.mapper;

import org.mapstruct.MappingTarget;
import java.util.List;

public interface BaseMapper<E,DO,DT> {
    DO toDomainFromEntity(E entity);
    E toEntity(DO domain);
    DT toDTO(DO domain);
    List<DT> toListDTO(List<DO> domain);
    DO toDomainFromDTO(DT dto);
    DT toDTOFromEntity(E entity);
    E toUpdateEntityMapper(DO domain, @MappingTarget E entity);
}
