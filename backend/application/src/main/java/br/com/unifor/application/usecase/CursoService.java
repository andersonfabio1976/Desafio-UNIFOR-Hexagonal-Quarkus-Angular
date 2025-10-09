package br.com.unifor.application.usecase;

import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Disciplina;
import br.com.unifor.domain.model.Semestre;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepositoryPort repositorio;

    // CRUD básico
    public Optional<Curso> buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public List<Curso> listarTodos() {
        return repositorio.listarTodos();
    }

    @Transactional
    public void salvar(Curso curso) {
        repositorio.salvar(curso);
    }

    @Transactional
    public boolean excluirPorId(Long id) {
        return repositorio.excluirPorId(id);
    }

    // ---------- Disciplinas (cascade via Curso) ----------

    @Transactional
    public void adicionarDisciplina(Long idCurso, Disciplina disciplina) {
        repositorio.buscarPorId(idCurso).ifPresent(curso -> {
            curso.adicionarDisciplina(disciplina);
            repositorio.salvar(curso);
        });
    }

    @Transactional
    public boolean removerDisciplina(Long idCurso, Long idDisciplina) {
        return repositorio.buscarPorId(idCurso)
                .map(curso -> {
                    boolean removida = curso.removerDisciplina(idDisciplina);
                    if (removida) {
                        repositorio.salvar(curso);
                    }
                    return removida;
                }).orElse(false);
    }

    public List<Disciplina> listarDisciplinas(Long idCurso) {
        return repositorio.buscarPorId(idCurso)
                .map(Curso::getDisciplinas)
                .orElse(List.of());
    }

    public Optional<Disciplina> buscarDisciplina(Long idCurso, Long idDisciplina) {
        return repositorio.buscarPorId(idCurso)
                .flatMap(curso -> curso.getDisciplinas().stream()
                        .filter(d -> d.getIdentifier().equals(idDisciplina))
                        .findFirst());
    }

    // ---------- Semestres (cascade via Curso) ----------

    @Transactional
    public void adicionarSemestre(Long idCurso, Semestre semestre) {
        repositorio.buscarPorId(idCurso).ifPresent(curso -> {
            curso.adicionarSemestre(semestre);
            repositorio.salvar(curso);
        });
    }

    @Transactional
    public boolean removerSemestre(Long idCurso, Long idSemestre) {
        return repositorio.buscarPorId(idCurso)
                .map(curso -> {
                    boolean removido = curso.removerSemestre(idSemestre);
                    if (removido) {
                        repositorio.salvar(curso);
                    }
                    return removido;
                }).orElse(false);
    }

    public List<Semestre> listarSemestres(Long idCurso) {
        return repositorio.buscarPorId(idCurso)
                .map(Curso::getSemestres)
                .orElse(List.of());
    }

    public Optional<Semestre> buscarSemestre(Long idCurso, Long idSemestre) {
        return repositorio.buscarPorId(idCurso)
                .flatMap(curso -> curso.getSemestres().stream()
                        .filter(s -> s.getIdentifier().equals(idSemestre))
                        .findFirst());
    }
}
