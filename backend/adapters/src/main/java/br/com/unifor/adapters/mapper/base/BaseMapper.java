package br.com.unifor.adapters.mapper.base;

import java.util.List;

public interface BaseMapper<E,DO,DT> {
    DO toDomainFromEntity(E entity);
    E toEntity(DO domain);
    DT toDTO(DO domain);
    List<DT> toDTO(List<DO> domain);
    DO toDomainFromDTO(DT dto);
}
