package br.com.unifor.application.usecase;

import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class DisciplinaService {

    private final CursoRepositoryPort cursoRepositorio;

    public Optional<Disciplina> buscarPorId(Long idCurso, Long idDisciplina) {
        return cursoRepositorio.buscarPorId(idCurso)
                .flatMap(curso -> curso.getDisciplinas().stream()
                        .filter(d -> d.getIdentifier().equals(idDisciplina))
                        .findFirst());
    }

    public List<Disciplina> listarPorCurso(Long idCurso) {
        return cursoRepositorio.buscarPorId(idCurso)
                .map(Curso::getDisciplinas)
                .orElse(List.of());
    }

    @Transactional
    public void salvar(Long idCurso, Disciplina disciplina) {
        cursoRepositorio.buscarPorId(idCurso).ifPresent(curso -> {
            curso.adicionarDisciplina(disciplina);
            cursoRepositorio.salvar(curso); // Cascade persiste as disciplinas
        });
    }

    @Transactional
    public boolean excluirPorId(Long idCurso, Long idDisciplina) {
        return cursoRepositorio.buscarPorId(idCurso)
                .map(curso -> {
                    boolean removida = curso.removerDisciplina(idDisciplina);
                    cursoRepositorio.salvar(curso); // Cascade remove do banco
                    return removida;
                }).orElse(false);
    }
}
