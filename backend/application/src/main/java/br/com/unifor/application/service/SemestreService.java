package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.SemestreRepositoryPort;
import br.com.unifor.domain.model.Semestre;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SemestreService extends BaseService<Semestre, SemestreRepositoryPort> {
    public SemestreService(SemestreRepositoryPort repositorio) {
        super(repositorio);
    }
}
