package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.SemestreMapper;
import br.com.unifor.adapters.repository.entity.SemestreEntity;
import br.com.unifor.application.port.repository.SemestreRepositoryPort;
import br.com.unifor.domain.model.Semestre;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SemestreRepositoryAdapter
        extends BaseRepositoryAdapter<SemestreEntity, Semestre>
        implements SemestreRepositoryPort {

    private final SemestreMapper mapper;

    @jakarta.inject.Inject
    public SemestreRepositoryAdapter(SemestreMapper mapper) {
        super(mapper::toDomainFromEntity, mapper::toEntity, mapper::toUpdateEntityMapper);
        this.mapper = mapper;
    }

}
