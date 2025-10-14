package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.mapper.DisciplinaMapper;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import br.com.unifor.application.port.repository.DisciplinaRepositoryPort;
import br.com.unifor.domain.model.Disciplina;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplinaRepositoryAdapter
        extends BaseRepositoryAdapter<DisciplinaEntity, Disciplina, DisciplinaDTO>
        implements DisciplinaRepositoryPort {

    private final DisciplinaMapper mapper;

    public DisciplinaRepositoryAdapter(DisciplinaMapper mapper) {
        super(mapper::toDomainFromEntity, mapper::toEntity, mapper::toUpdateEntityMapper);
        this.mapper = mapper;
    }
}
