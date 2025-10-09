package br.com.unifor.application.usecase;

import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.domain.model.Aluno;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepositoryPort repositorio;

    public Optional<Aluno> buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public List<Aluno> listarTodos() {
        return repositorio.listarTodos();
    }

    @Transactional
    public void salvar(Aluno aluno) {
        repositorio.salvar(aluno);
    }

    @Transactional
    public boolean excluirPorId(Long id) {
        return repositorio.excluirPorId(id);
    }
}
