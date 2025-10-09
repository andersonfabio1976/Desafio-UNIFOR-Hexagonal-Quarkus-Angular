package br.com.unifor.application.usecase;

import br.com.unifor.application.port.repository.ProfessorRepositoryPort;
import br.com.unifor.domain.model.Professor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepositoryPort repositorio;

    public Optional<Professor> buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public List<Professor> listarTodos() {
        return repositorio.listarTodos();
    }

    @Transactional
    public void salvar(Professor professor) {
        repositorio.salvar(professor);
    }

    @Transactional
    public boolean excluirPorId(Long id) {
        return repositorio.excluirPorId(id);
    }
}
