package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.DisciplinaRepositoryPort;
import br.com.unifor.domain.model.Disciplina;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DisciplinaService extends BaseService<Disciplina, DisciplinaRepositoryPort> {
    public DisciplinaService(DisciplinaRepositoryPort repositorio) {
        super(repositorio);
    }
}
