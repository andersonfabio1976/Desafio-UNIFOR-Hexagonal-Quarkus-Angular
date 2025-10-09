package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Semestre;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(force = true)
public class SemestreService {

    private final CursoRepositoryPort cursoRepositorio;

    public Optional<Semestre> buscarPorIdentifier(Long identifierCurso, Long identifierSemestre) {
        return cursoRepositorio.buscarPorIdentifier(identifierCurso)
                .flatMap(curso -> curso.getSemestres().stream()
                        .filter(s -> s.getIdentifier().equals(identifierSemestre))
                        .findFirst());
    }

    public List<Semestre> listarPorCurso(Long identifierCurso) {
        return cursoRepositorio.buscarPorIdentifier(identifierCurso)
                .map(Curso::getSemestres)
                .orElse(List.of());
    }

    @Transactional
    public void salvar(Long identifierCurso, Semestre semestre) {
        cursoRepositorio.buscarPorIdentifier(identifierCurso).ifPresent(curso -> {
            curso.adicionarSemestre(semestre);
            cursoRepositorio.salvar(curso);
        });
    }

    @Transactional
    public boolean excluirPorIdentifier(Long identifierCurso, Long identifierSemestre) {
        return cursoRepositorio.buscarPorIdentifier(identifierCurso)
                .map(curso -> {
                    boolean removido = curso.removerSemestre(identifierSemestre);
                    cursoRepositorio.salvar(curso);
                    return removido;
                }).orElse(false);
    }
}
