package br.com.unifor.adapters.config;

import br.com.unifor.adapters.mapper.*;
import br.com.unifor.adapters.repository.impl.*;
import br.com.unifor.application.port.repository.*;
import br.com.unifor.application.service.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServiceProducer {

    @Inject AlunoMapper alunoMapper;
    @Inject CursoMapper cursoMapper;
    @Inject ProfessorMapper professorMapper;
    @Inject SemestreMapper semestreMapper;
    @Inject DisciplinaMapper disciplinaMapper;

    @Produces
    @ApplicationScoped
    public AlunoService alunoService() {
        AlunoRepositoryPort repo = new AlunoRepositoryAdapter(alunoMapper);
        return new AlunoService(repo);
    }

    @Produces
    @ApplicationScoped
    public CursoService cursoService() {
        CursoRepositoryPort repo = new CursoRepositoryAdapter(cursoMapper);
        return new CursoService(repo);
    }

    @Produces
    @ApplicationScoped
    public ProfessorService professorService() {
        ProfessorRepositoryPort repo = new ProfessorRepositoryAdapter(professorMapper);
        return new ProfessorService(repo);
    }

    @Produces
    @ApplicationScoped
    public SemestreService semestreService() {
        SemestreRepositoryPort repo = new SemestreRepositoryAdapter(semestreMapper);
        return new SemestreService(repo);
    }

    @Produces
    @ApplicationScoped
    public DisciplinaService disciplinaService() {
        DisciplinaRepositoryPort repo = new DisciplinaRepositoryAdapter(disciplinaMapper);
        return new DisciplinaService(repo);
    }

}
