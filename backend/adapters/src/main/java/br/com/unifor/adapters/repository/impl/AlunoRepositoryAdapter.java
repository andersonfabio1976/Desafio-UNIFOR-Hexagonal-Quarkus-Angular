package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.AlunoMapper;
import br.com.unifor.adapters.repository.entity.AlunoEntity;
import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.domain.model.Aluno;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlunoRepositoryAdapter extends BaseRepositoryAdapter<AlunoEntity, Aluno>
        implements AlunoRepositoryPort {

    private final AlunoMapper mapper;

    @jakarta.inject.Inject
    public AlunoRepositoryAdapter(AlunoMapper mapper) {
        super(mapper::toDomainFromEntity, mapper::toEntity);
        this.mapper = mapper;
    }
}
