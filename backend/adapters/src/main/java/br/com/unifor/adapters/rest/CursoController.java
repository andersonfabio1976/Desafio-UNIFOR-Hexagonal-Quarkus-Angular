package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.CursoDTO;
import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.mapper.CursoMapper;
import br.com.unifor.application.service.CursoService;
import br.com.unifor.domain.model.Curso;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/Cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"ADMIN", "ROLE_ADMINISTRADOR"})
@PermitAll
public class CursoController {

    @Inject
    CursoService service;

    @Inject
    CursoMapper mapper;

    @GET
    public List<CursoDTO> listarTodos() {
        return mapper.toListDTO(service.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public CursoDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return service.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
    }

    @POST
    @Transactional
    public Response salvar(CursoDTO dto) {
        Curso dominio = mapper.toDomainFromDTO(dto);
        service.salvar(dominio);
        CursoDTO resposta = mapper.toDTO(dominio);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/Cursos/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @PUT
    @Path("/{identifier}")
    @Transactional
    public Response atualizar(@PathParam("identifier") Long identifier, CursoDTO dto) {
        service.atualizar(mapper.toDomainFromDTO(dto), identifier);
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Curso não encontrado");
        }
        return Response.noContent().build();
    }
}