package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.CursoMapper;
import br.com.unifor.adapters.repository.entity.CursoEntity;
import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class CursoRepositoryAdapter implements CursoRepositoryPort, PanacheRepository<CursoEntity> {

    private final CursoMapper mapper;

    @Override
    public Optional<Curso> buscarPorId(Long id) {
        return findByIdOptional(id).map(mapper::toDomain);
    }

    @Override
    public List<Curso> listarTodos() {
        return listAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional
    public void salvar(Curso curso) {
        persist(mapper.toEntity(curso));
    }

    @Override
    @Transactional
    public boolean excluirPorId(Long id) {
        return deleteById(id);
    }
}
