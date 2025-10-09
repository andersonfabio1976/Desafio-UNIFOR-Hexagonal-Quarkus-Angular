package br.com.unifor.adapters.config;

import br.com.unifor.adapters.mapper.*;
import br.com.unifor.adapters.repository.impl.*;
import br.com.unifor.application.port.repository.*;
import br.com.unifor.application.service.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ServiceProducer {

    private final AlunoMapper alunoMapper;
    private final CursoMapper cursoMapper;
    private final ProfessorMapper professorMapper;
    private final SemestreMapper semestreMapper;
    private final MatriculaMapper matriculaMapper;
    private final DisciplinaMapper disciplinaMapper;


    public ServiceProducer(
            AlunoMapper alunoMapper,
            CursoMapper cursoMapper,
            ProfessorMapper professorMapper,
            SemestreMapper semestreMapper,
            MatriculaMapper matriculaMapper,
            DisciplinaMapper disciplinaMapper
    ) {
        this.alunoMapper = alunoMapper;
        this.cursoMapper = cursoMapper;
        this.professorMapper = professorMapper;
        this.semestreMapper = semestreMapper;
        this.matriculaMapper = matriculaMapper;
        this.disciplinaMapper = disciplinaMapper;

    }

    @Produces
    public AlunoService alunoService() {
        AlunoRepositoryPort repo = new AlunoRepositoryAdapter(alunoMapper);
        return new AlunoService(repo);
    }

    @Produces
    public CursoService cursoService() {
        CursoRepositoryPort repo = new CursoRepositoryAdapter(cursoMapper);
        return new CursoService(repo);
    }

    @Produces
    public ProfessorService professorService() {
        ProfessorRepositoryPort repo = new ProfessorRepositoryAdapter(professorMapper);
        return new ProfessorService(repo);
    }

    @Produces
    public SemestreService semestreService() {
        SemestreRepositoryPort repo = new SemestreRepositoryAdapter(semestreMapper);
        return new SemestreService();
    }

    @Produces
    public MatriculaService matriculaService() {
        MatriculaRepositoryPort repo = new MatriculaRepositoryAdapter(matriculaMapper);
        return new MatriculaService();
    }

    @Produces
    public DisciplinaService disciplinaService() {
        DisciplinaRepositoryPort repo = new DisciplinaRepositoryAdapter(disciplinaMapper);
        return new DisciplinaService();
    }

}
