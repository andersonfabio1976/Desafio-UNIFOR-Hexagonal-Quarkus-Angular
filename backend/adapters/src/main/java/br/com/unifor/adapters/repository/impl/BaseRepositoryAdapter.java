package br.com.unifor.adapters.repository.impl;

import br.com.unifor.application.port.repository.BaseRepositoryPort;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

@NoArgsConstructor(force = true)
public abstract class BaseRepositoryAdapter<E, D> implements BaseRepositoryPort<D, Long>, PanacheRepository<E> {

    private final Function<E, D> toDomainMapper;
    private final Function<D, E> toEntityMapper;
    private final BiFunction<D,E,E> toUpdateEntityMapper;

    protected BaseRepositoryAdapter(Function<E, D> toDomainMapper,
                                    Function<D, E> toEntityMapper, BiFunction<D, E,E> toUpdateEntityMapper) {
        this.toDomainMapper = toDomainMapper;
        this.toEntityMapper = toEntityMapper;
        this.toUpdateEntityMapper = toUpdateEntityMapper;
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
    public void atualizar(D domain, Long identifier) {
        E entity = findByIdOptional(identifier)
                .orElseThrow(() -> new NotFoundException("Registro não encontrado"));
        toUpdateEntityMapper.apply(domain, entity);
        persist(entity);
    }

    @Transactional
    public boolean excluirPorIdentifier(Long identifier) {
        return deleteById(identifier);
    }

}
