package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.BaseRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public abstract class BaseService<D,RP extends BaseRepositoryPort<D, Long>> {

    private final RP repositorio;
    public Optional<D> buscarPorIdentifier(Long identifier) {
        return repositorio.buscarPorIdentifier(identifier);
    }
    public List<D> listarTodos() {
        return repositorio.listarTodos();
    }

    @Transactional
    public void salvar(D domain) {
        repositorio.salvar(domain);
    }

    @Transactional
    public boolean excluirPorIdentifier(Long identifier) {
        return repositorio.excluirPorIdentifier(identifier);
    }
}
