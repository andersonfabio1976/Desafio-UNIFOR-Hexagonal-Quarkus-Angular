package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Matricula;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(force = true)
public class MatriculaService {

    private final AlunoRepositoryPort alunoRepositorio;
    private final CursoRepositoryPort cursoRepositorio;

    public Optional<Matricula> buscarPorIdentifier(Long identifierAluno, Long identifierMatricula) {
        return alunoRepositorio.buscarPorIdentifier(identifierAluno)
                .flatMap(aluno -> aluno.getMatriculas().stream()
                        .filter(m -> m.getIdentifier().equals(identifierMatricula))
                        .findFirst());
    }

    public List<Matricula> listarPorAluno(Long identifierAluno) {
        return alunoRepositorio.buscarPorIdentifier(identifierAluno)
                .map(Aluno::getMatriculas)
                .orElse(List.of());
    }

    @Transactional
    public void salvar(Long identifierAluno, Long identifierCurso, Matricula matricula) {
        Optional<Aluno> alunoOpt = alunoRepositorio.buscarPorIdentifier(identifierAluno);
        Optional<Curso> cursoOpt = cursoRepositorio.buscarPorIdentifier(identifierCurso);

        if (alunoOpt.isPresent() && cursoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            Curso curso = cursoOpt.get();

            aluno.matricularCurso(curso);
            alunoRepositorio.salvar(aluno);
        }
    }

    @Transactional
    public boolean excluirPorIdentifier(Long identifierAluno, Long identifierMatricula) {
        return alunoRepositorio.buscarPorIdentifier(identifierAluno)
                .map(aluno -> {
                    boolean removida = aluno.getMatriculas()
                            .removeIf(m -> m.getIdentifier().equals(identifierMatricula));
                    alunoRepositorio.salvar(aluno);
                    return removida;
                }).orElse(false);
    }
}
