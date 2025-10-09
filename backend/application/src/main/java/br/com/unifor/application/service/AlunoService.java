package br.com.unifor.application.service;

import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.domain.model.Aluno;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlunoService extends BaseService<Aluno, AlunoRepositoryPort> {
    public AlunoService(AlunoRepositoryPort repositorio) {
        super(repositorio);
    }
}
