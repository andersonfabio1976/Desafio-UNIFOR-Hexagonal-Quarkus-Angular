package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.AlunoMapper;
import br.com.unifor.adapters.repository.entity.AlunoEntity;
import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.domain.model.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class AlunoRepositoryAdapter implements AlunoRepositoryPort, PanacheRepository<AlunoEntity> {

    private final AlunoMapper mapper;

    @Override
    public Optional<Aluno> buscarPorId(Long id) {
        return findByIdOptional(id).map(mapper::toDomain);
    }

    @Override
    public List<Aluno> listarTodos() {
        return listAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional
    public void salvar(Aluno aluno) {
        persist(mapper.toEntity(aluno));
    }

    @Override
    @Transactional
    public boolean excluirPorId(Long id) {
        return deleteById(id);
    }
}
