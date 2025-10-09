package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.mapper.MatriculaMapper;
import br.com.unifor.application.service.MatriculaService;
import br.com.unifor.domain.model.Matricula;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@Path("/alunos/{idAluno}/cursos/{idCurso}/matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatriculaController {

    @Inject
    MatriculaService service;

    @Inject
    MatriculaMapper mapper;

    @GET
    @Path("/{idMatricula}")
    public Response buscarPorIdentifier(@PathParam("identifierAluno") Long identifierAluno,
                                @PathParam("identifierCurso") Long identifierCurso,
                                @PathParam("identifierMatricula") Long identifierMatricula) {
        Optional<MatriculaDTO> matricula = service.buscarPorIdentifier(identifierAluno, identifierMatricula)
                .map(mapper::toDTO);
        return matricula.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response salvar(@PathParam("identifierAluno") Long identifierAluno,
                           @PathParam("identifierCurso") Long identifierCurso,
                           MatriculaDTO dto) {
        Matricula matricula = mapper.toDomain(dto);
        service.salvar(identifierAluno, identifierCurso, matricula);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{idMatricula}")
    public Response removerPorIdentifier(@PathParam("identifierAluno") Long identifierAluno,
                                 @PathParam("identifierMatricula") Long identifierMatricula) {
        boolean removido = service.excluirPorIdentifier(identifierAluno, identifierMatricula);
        return removido ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
