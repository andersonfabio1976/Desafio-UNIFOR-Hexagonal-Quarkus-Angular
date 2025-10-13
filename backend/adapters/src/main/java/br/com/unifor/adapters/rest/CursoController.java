package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.CursoDTO;
import br.com.unifor.adapters.mapper.CursoMapper;
import br.com.unifor.application.service.CursoService;
import br.com.unifor.domain.model.Curso;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ROLE_ADMIN", "ROLE_COORDENADOR"})
public class CursoController {

    @Inject
    CursoService servico;

    @Inject
    CursoMapper mapper;

    @GET
    public List<CursoDTO> listarTodos() {
        return mapper.toDTO(servico.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public CursoDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return servico.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
    }

    @POST
    @Transactional
    public void salvar(CursoDTO dto) {
        Curso curso = mapper.toDomainFromEntity(mapper.toEntity(mapper.toDomainFromDTO(dto)));
        servico.salvar(curso);
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public boolean removerPorId(@PathParam("identifier") Long identifier) {
        return servico.excluirPorIdentifier(identifier);
    }
}
