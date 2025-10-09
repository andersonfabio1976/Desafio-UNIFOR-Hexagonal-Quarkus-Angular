package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.domain.model.Curso;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CursoService extends BaseService<Curso, CursoRepositoryPort> {
    public CursoService(CursoRepositoryPort repositorio) {
        super(repositorio);
    }
}
