package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.CursoMapper;
import br.com.unifor.adapters.repository.entity.CursoEntity;
import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;

@ApplicationScoped
public class CursoRepositoryAdapter extends BaseRepositoryAdapter<CursoEntity, Curso>
        implements CursoRepositoryPort {

    private final CursoMapper mapper;

    @jakarta.inject.Inject
    public CursoRepositoryAdapter(CursoMapper mapper) {
        super(mapper::toDomain, mapper::toEntity);
        this.mapper = mapper;
    }
}
