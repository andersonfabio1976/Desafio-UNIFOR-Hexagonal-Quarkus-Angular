package br.com.unifor.adapters.service;

import br.com.unifor.adapters.repository.impl.ProfessorRepositoryAdapter;
import br.com.unifor.application.port.repository.ProfessorRepositoryPort;
import br.com.unifor.application.service.ProfessorService;
import br.com.unifor.domain.model.Professor;
import jakarta.enterprise.inject.spi.CDI;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ProfessorServiceUnitTest extends BaseServiceTest<Professor, ProfessorRepositoryPort, ProfessorService> {

    private boolean useMock = true;

    @BeforeEach
    void setupMode() {

    }

    @Override
    protected ProfessorRepositoryPort getRepositoryInstance() {
        if (useMock) {
            return Mockito.mock(ProfessorRepositoryPort.class);
        } else {
            return CDI.current().select(ProfessorRepositoryAdapter.class).get();
        }
    }

    @Override
    protected ProfessorService getServiceInstance(ProfessorRepositoryPort repository) {
        return new ProfessorService(repository);
    }

    @Override
    protected Professor createSampleDomain() {
        return Professor.builder()
                .identifier(1L)
                .nome("Joaquim de Almeida")
                .email("joaquim@gmail")
                .build();
    }
}
