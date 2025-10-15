package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.MatriculaRepositoryPort;
import br.com.unifor.domain.model.Matricula;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MatriculaService extends BaseService<Matricula, MatriculaRepositoryPort> {
    public MatriculaService(MatriculaRepositoryPort repositorio) {
        super(repositorio);
    }
}
