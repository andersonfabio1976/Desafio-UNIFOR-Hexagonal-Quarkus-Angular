package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.mapper.MatriculaMapper;
import br.com.unifor.adapters.repository.entity.MatriculaEntity;
import br.com.unifor.application.port.repository.MatriculaRepositoryPort;
import br.com.unifor.domain.model.Matricula;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MatriculaRepositoryAdapter
        extends BaseRepositoryAdapter<MatriculaEntity, Matricula>
        implements MatriculaRepositoryPort {

    private final MatriculaMapper mapper;

    @jakarta.inject.Inject
    public MatriculaRepositoryAdapter(MatriculaMapper mapper) {
        super(mapper::toDomainFromEntity, mapper::toEntity, mapper::toUpdateEntityMapper);
        this.mapper = mapper;
    }

}
