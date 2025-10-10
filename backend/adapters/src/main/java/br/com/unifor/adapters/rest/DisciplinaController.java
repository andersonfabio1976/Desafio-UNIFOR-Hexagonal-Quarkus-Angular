package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.mapper.DisciplinaMapper;
import br.com.unifor.application.service.DisciplinaService;
import br.com.unifor.domain.model.Disciplina;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cursos/{identifierCurso}/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinaController {

    @Inject
    DisciplinaService service;

    @Inject
    DisciplinaMapper mapper;

    @GET
    public Response listar(@PathParam("identifierCurso") Long identifierCurso) {
        List<DisciplinaDTO> disciplinas = service.listarPorCurso(identifierCurso)
                .stream()
                .map(mapper::toDTO)
                .toList();
        return Response.ok(disciplinas).build();
    }

    @GET
    @Path("/{identifierDisciplina}")
    public Response buscarPorIdentifier(@PathParam("identifierCurso") Long identifierCurso,
                                @PathParam("identifierDisciplina") Long identifierDisciplina) {
        return service.buscarPorIdentifier(identifierCurso, identifierDisciplina)
                .map(mapper::toDTO)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response salvar(@PathParam("identifierCurso") Long identifierCurso, DisciplinaDTO dto) {
        Disciplina disciplina = mapper.toDomain(dto);
        service.salvar(identifierCurso, disciplina);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{identifierDisciplina}")
    public Response removerPorIdentifier(@PathParam("identifierCurso") Long identifierCurso,
                                 @PathParam("identifierDisciplina") Long identifierDisciplina) {
        boolean removido = service.excluirPorIdentifier(identifierCurso, identifierDisciplina);
        return removido ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
