package br.com.unifor.adapters.service;

import br.com.unifor.adapters.repository.impl.CursoRepositoryAdapter;
import br.com.unifor.application.port.repository.CursoRepositoryPort;
import br.com.unifor.application.service.CursoService;
import br.com.unifor.domain.model.Curso;
import jakarta.enterprise.inject.spi.CDI;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class CursoServiceUnitTest extends BaseServiceTest<Curso, CursoRepositoryPort, CursoService> {

    private boolean useMock = true;

    @BeforeEach
    void setupMode() {

    }

    @Override
    protected CursoRepositoryPort getRepositoryInstance() {
        if (useMock) {
            return Mockito.mock(CursoRepositoryPort.class);
        } else {
            return CDI.current().select(CursoRepositoryAdapter.class).get();
        }
    }

    @Override
    protected CursoService getServiceInstance(CursoRepositoryPort repository) {
        return new CursoService(repository);
    }

    @Override
    protected Curso createSampleDomain() {
        return Curso.builder()
                .identifier(1L)
                .nome("Joaquim de Almeida")
                .build();
    }
}
