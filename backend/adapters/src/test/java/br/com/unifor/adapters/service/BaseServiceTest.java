package br.com.unifor.adapters.service;

import br.com.unifor.application.port.repository.BaseRepositoryPort;
import br.com.unifor.application.service.BaseService;
import io.quarkus.test.TestTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@QuarkusTest
public abstract class BaseServiceTest<D, RP extends BaseRepositoryPort<D, Long>, S extends BaseService<D, RP>> {

    protected RP repositorio;
    protected S service;
    protected abstract RP getRepositoryInstance();
    protected abstract S getServiceInstance(RP repository);
    protected abstract D createSampleDomain();

    @BeforeEach
    void setup() {
        repositorio = getRepositoryInstance();
        service = getServiceInstance(repositorio);
    }

    private boolean isMockedRepository() {
        return Mockito.mockingDetails(repositorio).isMock();
    }

    @Test
    @TestTransaction
    void testSalvar() {
        D domain = createSampleDomain();

        if (isMockedRepository()) {
            doNothing().when(repositorio).salvar(domain);
            service.salvar(domain);
            verify(repositorio, times(1)).salvar(domain);
        } else {
            assertDoesNotThrow(() -> service.salvar(domain));
        }
    }

    @Test
    @TestTransaction
    void testBuscarPorIdentifier() {
        D domain = createSampleDomain();

        if (isMockedRepository()) {
            when(repositorio.buscarPorIdentifier(1L)).thenReturn(Optional.of(domain));
        }
        Optional<D> resultado = service.buscarPorIdentifier(1L);

        if (isMockedRepository()) {
            assertTrue(resultado.isPresent());
            assertEquals(domain, resultado.get());
            verify(repositorio, times(1)).buscarPorIdentifier(1L);
        } else {
            assertNotNull(resultado);
        }
    }

    @Test
    @TestTransaction
    void testListarTodos() {
        if (isMockedRepository()) {
            when(repositorio.listarTodos()).thenReturn(List.of(createSampleDomain()));
        }
        List<D> resultado = service.listarTodos();
        assertNotNull(resultado);
        if (isMockedRepository()) {
            assertFalse(resultado.isEmpty());
            verify(repositorio, times(1)).listarTodos();
        }
    }

    @Test
    @TestTransaction
    void testExcluirPorIdentifier() {
        if (isMockedRepository()) {
            when(repositorio.excluirPorIdentifier(1L)).thenReturn(true);
        }
        boolean sucesso = service.excluirPorIdentifier(1L);

        if (isMockedRepository()) {
            assertTrue(sucesso);
            verify(repositorio, times(1)).excluirPorIdentifier(1L);
        } else {
            assertDoesNotThrow(() -> service.excluirPorIdentifier(1L));
        }
    }
}
