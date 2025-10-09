package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.ProfessorMapper;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.application.port.repository.ProfessorRepositoryPort;
import br.com.unifor.domain.model.Professor;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorRepositoryAdapter extends BaseRepositoryAdapter<ProfessorEntity, Professor>
        implements ProfessorRepositoryPort {

    private final ProfessorMapper mapper;

    @jakarta.inject.Inject
    public ProfessorRepositoryAdapter(ProfessorMapper mapper) {
        super(mapper::toDomainFromEntity, mapper::toEntity);
        this.mapper = mapper;
    }
}
