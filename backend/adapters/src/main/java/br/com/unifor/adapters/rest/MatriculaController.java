package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.mapper.MatriculaMapper;
import br.com.unifor.application.service.MatriculaService;
import br.com.unifor.domain.model.Matricula;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/Matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"ADMIN", "ROLE_ADMIN", "ROLE_ADMINISTRADOR"})
public class MatriculaController {

    @Inject
    MatriculaService service;

    @Inject
    MatriculaMapper mapper;

    @GET
    public List<MatriculaDTO> listarTodos() {
        return mapper.toListDTO(service.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public MatriculaDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return service.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Matricula não encontrado"));
    }

    @POST
    @Transactional
    public Response salvar(MatriculaDTO dto) {
        Matricula dominio = mapper.toDomainFromDTO(dto);
        service.salvar(dominio);
        MatriculaDTO resposta = mapper.toDTO(dominio);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/Matriculas/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @PUT
    @Path("/{identifier}")
    @Transactional
    public Response atualizar(@PathParam("identifier") Long identifier, MatriculaDTO dto) {
        service.atualizar(mapper.toDomainFromDTO(dto), identifier);
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Matricula não encontrado");
        }
        return Response.noContent().build();
    }
}