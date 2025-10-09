package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(force = true)
public class DisciplinaService {

    private final CursoRepositoryPort cursoRepositorio;

    public Optional<Disciplina> buscarPorIdentifier(Long identifierCurso, Long identifierDisciplina) {
        return cursoRepositorio.buscarPorIdentifier(identifierCurso)
                .flatMap(curso -> curso.getDisciplinas().stream()
                        .filter(d -> d.getIdentifier().equals(identifierDisciplina))
                        .findFirst());
    }

    public List<Disciplina> listarPorCurso(Long identifierCurso) {
        return cursoRepositorio.buscarPorIdentifier(identifierCurso)
                .map(Curso::getDisciplinas)
                .orElse(List.of());
    }

    @Transactional
    public void salvar(Long identifierCurso, Disciplina disciplina) {
        cursoRepositorio.buscarPorIdentifier(identifierCurso).ifPresent(curso -> {
            curso.adicionarDisciplina(disciplina);
            cursoRepositorio.salvar(curso);
        });
    }

    @Transactional
    public boolean excluirPorIdentifier(Long identifierCurso, Long identifierDisciplina) {
        return cursoRepositorio.buscarPorIdentifier(identifierCurso)
                .map(curso -> {
                    boolean removida = curso.removerDisciplina(identifierDisciplina);
                    cursoRepositorio.salvar(curso);
                    return removida;
                }).orElse(false);
    }
}
