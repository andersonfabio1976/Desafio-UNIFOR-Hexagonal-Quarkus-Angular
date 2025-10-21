package br.com.unifor.application.port.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepositoryPort<D, ID> {
    Optional<D> buscarPorIdentifier(ID identifier);
    List<D> listarTodos();
    void salvar(D domain);
    boolean excluirPorIdentifier(ID identifier);
    void atualizar(D domain, ID identifier);
}
