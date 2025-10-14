package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.MatriculaDTO;
import br.com.unifor.adapters.mapper.MatriculaMapper;
import br.com.unifor.application.service.MatriculaService;
import br.com.unifor.domain.model.Matricula;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/alunos/{idAluno}/cursos/{idCurso}/matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"ADMIN", "ROLE_ADMINISTRADOR"})
public class MatriculaController {

    @Inject
    MatriculaService service;

    @Inject
    MatriculaMapper mapper;

    @GET
    @Path("/{identifierAluno}/{identifierCurso}/{identifierMatricula}")
    public Response buscarPorIdentifier(@PathParam("identifierAluno") Long identifierAluno,
                                @PathParam("identifierCurso") Long identifierCurso,
                                @PathParam("identifierMatricula") Long identifierMatricula) {
        Optional<MatriculaDTO> matricula = service.buscarPorIdentifier(identifierAluno, identifierMatricula)
                .map(mapper::toDTO);
        return matricula.map(Response::ok)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"))
                .build();
    }

    @POST
    @Path("/{identifierAluno},/{identifierCurso}")
    public Response salvar(@PathParam("identifierAluno") Long identifierAluno,
                           @PathParam("identifierCurso") Long identifierCurso,
                           MatriculaDTO dto) {
        Matricula matricula = mapper.toDomainFromDTO(dto);
        service.salvar(identifierAluno, identifierCurso, matricula);

        if (dto.getIdentifier() == null && matricula != null) {
            return Response
                    .created(URI.create("/alunos/" + matricula.toString()))
                    .entity(matricula)
                    .build();
        }
        return Response.ok(matricula).build();
    }

    @DELETE
    @Path("/{identifierAluno}/{identifierMatricula}")
    public Response removerPorIdentifier(@PathParam("identifierAluno") Long identifierAluno,
                                 @PathParam("identifierMatricula") Long identifierMatricula) {
        boolean removido = service.excluirPorIdentifier(identifierAluno, identifierMatricula);
        if (!removido) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return Response.noContent().build();
    }
}
