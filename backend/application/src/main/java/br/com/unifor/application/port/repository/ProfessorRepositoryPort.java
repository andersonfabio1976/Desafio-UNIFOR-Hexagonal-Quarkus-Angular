package br.com.unifor.application.port.repository;

import br.com.unifor.domain.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepositoryPort {

    Optional<Professor> buscarPorId(Long id);

    List<Professor> listarTodos();

    void salvar(Professor professor);

    boolean excluirPorId(Long id);
}
