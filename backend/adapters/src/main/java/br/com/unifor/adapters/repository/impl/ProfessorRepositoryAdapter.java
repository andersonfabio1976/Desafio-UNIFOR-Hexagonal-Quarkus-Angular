package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.mapper.ProfessorMapper;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.application.port.repository.ProfessorRepositoryPort;
import br.com.unifor.domain.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ProfessorRepositoryAdapter implements ProfessorRepositoryPort, PanacheRepository<ProfessorEntity> {

    private final ProfessorMapper mapper;

    @Override
    public Optional<Professor> buscarPorId(Long id) {
        return findByIdOptional(id).map(mapper::toDomain);
    }

    @Override
    public List<Professor> listarTodos() {
        return listAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional
    public void salvar(Professor professor) {
        persist(mapper.toEntity(professor));
    }

    @Override
    @Transactional
    public boolean excluirPorId(Long id) {
        return deleteById(id);
    }
}
