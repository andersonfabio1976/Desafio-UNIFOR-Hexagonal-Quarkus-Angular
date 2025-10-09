package br.com.unifor.application.port.repository;

import br.com.unifor.domain.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepositoryPort {

    Optional<Curso> buscarPorId(Long id);

    List<Curso> listarTodos();

    void salvar(Curso curso);

    boolean excluirPorId(Long id);
}
