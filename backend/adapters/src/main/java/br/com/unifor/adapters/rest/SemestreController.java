package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.mapper.SemestreMapper;
import br.com.unifor.application.service.SemestreService;
import br.com.unifor.domain.model.Semestre;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cursos/{idCurso}/semestres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ROLE_ADMIN", "ROLE_COORDENADOR"})
public class SemestreController {

    @Inject
    SemestreService service;

    @Inject
    SemestreMapper mapper;

    @GET
    public Response listar(@PathParam("identifierCurso") Long identifierCurso) {
        List<SemestreDTO> semestres = service.listarPorCurso(identifierCurso)
                .stream()
                .map(mapper::toDTO)
                .toList();
        return Response.ok(semestres).build();
    }

    @GET
    @Path("/{identifierSemestre}")
    public Response buscarPorIdentifier(@PathParam("identifierCurso") Long identifierCurso,
                                @PathParam("identifierSemestre") Long identifierSemestre) {
        return service.buscarPorIdentifier(identifierCurso, identifierSemestre)
                .map(mapper::toDTO)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response salvar(@PathParam("identifierCurso") Long identifierCurso, SemestreDTO dto) {
        Semestre semestre = mapper.toDomainFromDTO(dto);
        service.salvar(identifierCurso, semestre);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{identifierSemestre}")
    public Response removerPorIdentifier(@PathParam("identifierCurso") Long identifierCurso,
                                 @PathParam("identifierSemestre") Long identifierSemestre) {
        boolean removido = service.excluirPorIdentifier(identifierCurso, identifierSemestre);
        return removido ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
