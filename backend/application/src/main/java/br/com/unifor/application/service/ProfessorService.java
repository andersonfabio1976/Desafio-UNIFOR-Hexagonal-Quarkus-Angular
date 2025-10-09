package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.ProfessorRepositoryPort;
import br.com.unifor.domain.model.Professor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProfessorService extends BaseService<Professor, ProfessorRepositoryPort> {
    public ProfessorService(ProfessorRepositoryPort repositorio) {
        super(repositorio);
    }
}
