package br.com.unifor.adapters.service;

import br.com.unifor.adapters.repository.impl.AlunoRepositoryAdapter;
import br.com.unifor.application.port.repository.AlunoRepositoryPort;
import br.com.unifor.application.service.AlunoService;
import br.com.unifor.domain.model.Aluno;
import jakarta.enterprise.inject.spi.CDI;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.time.LocalDate;

public class AlunoServiceUnitTest extends BaseServiceTest<Aluno, AlunoRepositoryPort, AlunoService> {

    private boolean useMock = true;

    @BeforeEach
    void setupMode() {

    }

    @Override
    protected AlunoRepositoryPort getRepositoryInstance() {
        if (useMock) {
            return Mockito.mock(AlunoRepositoryPort.class);
        } else {
            return CDI.current().select(AlunoRepositoryAdapter.class).get();
        }
    }

    @Override
    protected AlunoService getServiceInstance(AlunoRepositoryPort repository) {
        return new AlunoService(repository);
    }

    @Override
    protected Aluno createSampleDomain() {
        return Aluno.builder()
                .identifier(1L)
                .nome("Joaquim de Almeida")
                .dataNascimento(LocalDate.ofYearDay(1976,03))
                .email("joaquim@gmail")
                .build();
    }
}
