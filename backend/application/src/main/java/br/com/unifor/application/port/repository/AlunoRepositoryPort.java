package br.com.unifor.application.port.repository;

import br.com.unifor.domain.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepositoryPort {

    Optional<Aluno> buscarPorId(Long id);

    List<Aluno> listarTodos();

    void salvar(Aluno aluno);

    boolean excluirPorId(Long id);
}
