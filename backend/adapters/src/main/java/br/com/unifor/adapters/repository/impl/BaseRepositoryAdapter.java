package br.com.unifor.adapters.repository.impl;

import br.com.unifor.application.port.repository.BaseRepositoryPort;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(force = true)
public abstract class BaseRepositoryAdapter<E, D> implements BaseRepositoryPort<D,Long>, PanacheRepository<E> {

    private final Function<E, D> toDomainMapper;
    private final Function<D, E> toEntityMapper;

    protected BaseRepositoryAdapter(Function<E, D> toDomainMapper,
                                    Function<D, E> toEntityMapper) {
        this.toDomainMapper = toDomainMapper;
        this.toEntityMapper = toEntityMapper;
    }

    public Optional<D> buscarPorIdentifier(Long identifier) {
        return findByIdOptional(identifier).map(toDomainMapper);
    }

    public List<D> listarTodos() {
        return listAll().stream().map(toDomainMapper).toList();
    }

    @Transactional
    public void salvar(D dto) {
        persist(toEntityMapper.apply(dto));
    }

    @Transactional
    public boolean excluirPorIdentifier(Long identifier) {
        return deleteById(identifier);
    }
}
