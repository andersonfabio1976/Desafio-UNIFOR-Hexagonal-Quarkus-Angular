package br.com.unifor.application.usecase;

import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Semestre;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class SemestreService {

    private final CursoRepositoryPort cursoRepositorio;

    public Optional<Semestre> buscarPorId(Long idCurso, Long idSemestre) {
        return cursoRepositorio.buscarPorId(idCurso)
                .flatMap(curso -> curso.getSemestres().stream()
                        .filter(s -> s.getIdentifier().equals(idSemestre))
                        .findFirst());
    }

    public List<Semestre> listarPorCurso(Long idCurso) {
        return cursoRepositorio.buscarPorId(idCurso)
                .map(Curso::getSemestres)
                .orElse(List.of());
    }

    @Transactional
    public void salvar(Long idCurso, Semestre semestre) {
        cursoRepositorio.buscarPorId(idCurso).ifPresent(curso -> {
            curso.adicionarSemestre(semestre);
            cursoRepositorio.salvar(curso); // Cascade persiste os semestres
        });
    }

    @Transactional
    public boolean excluirPorId(Long idCurso, Long idSemestre) {
        return cursoRepositorio.buscarPorId(idCurso)
                .map(curso -> {
                    boolean removido = curso.removerSemestre(idSemestre);
                    cursoRepositorio.salvar(curso); // Cascade sincroniza
                    return removido;
                }).orElse(false);
    }
}
