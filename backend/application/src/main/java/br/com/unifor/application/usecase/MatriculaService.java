package br.com.unifor.application.usecase;

import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Curso;
import br.com.unifor.domain.model.Matricula;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class MatriculaService {

    private final AlunoRepositoryPort alunoRepositorio;
    private final CursoRepositoryPort cursoRepositorio;

    @Transactional
    public void matricularAlunoEmCurso(Long idAluno, Long idCurso) {
        Optional<Aluno> alunoOpt = alunoRepositorio.buscarPorId(idAluno);
        Optional<Curso> cursoOpt = cursoRepositorio.buscarPorId(idCurso);

        if (alunoOpt.isPresent() && cursoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            Curso curso = cursoOpt.get();
            aluno.matricularCurso(curso);
            alunoRepositorio.salvar(aluno); // Cascade persiste a matrícula junto
        }
    }

    public Optional<Matricula> buscarPorAluno(Long idAluno, Long idMatricula) {
        return alunoRepositorio.buscarPorId(idAluno)
                .flatMap(aluno -> aluno.getMatriculas().stream()
                        .filter(m -> m.getIdentifier().equals(idMatricula))
                        .findFirst());
    }

    @Transactional
    public boolean cancelarMatricula(Long idAluno, Long idMatricula) {
        return alunoRepositorio.buscarPorId(idAluno)
                .map(aluno -> {
                    boolean removida = aluno.getMatriculas()
                            .removeIf(m -> m.getIdentifier().equals(idMatricula));
                    alunoRepositorio.salvar(aluno); // Atualiza cascade
                    return removida;
                }).orElse(false);
    }
}
