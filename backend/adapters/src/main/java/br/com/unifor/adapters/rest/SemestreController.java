package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.mapper.SemestreMapper;
import br.com.unifor.application.service.SemestreService;
import br.com.unifor.domain.model.Semestre;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/alunos/{idAluno}/cursos/{idCurso}/Semestres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"ADMIN", "ROLE_ADMINISTRADOR"})
public class SemestreController {

    @Inject
    SemestreService service;

    @Inject
    SemestreMapper mapper;

    @GET
    @Path("/{identifierAluno}/{identifierCurso}/{identifierSemestre}")
    public Response buscarPorIdentifier(@PathParam("identifierAluno") Long identifierAluno,
                                        @PathParam("identifierCurso") Long identifierCurso,
                                        @PathParam("identifierSemestre") Long identifierSemestre) {
        Optional<SemestreDTO> Semestre = service.buscarPorIdentifier(identifierAluno, identifierSemestre)
                .map(mapper::toDTO);
        return Semestre.map(Response::ok)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"))
                .build();
    }

    @POST
    @Path("/{identifierAluno},/{identifierCurso}")
    public Response salvar(@PathParam("identifierAluno") Long identifierAluno,
                           @PathParam("identifierCurso") Long identifierCurso,
                           SemestreDTO dto) {
        Semestre Semestre = mapper.toDomainFromDTO(dto);
        service.salvar(identifierCurso, Semestre);
        SemestreDTO resposta = mapper.toDTO(Semestre);

        if (dto.getIdentifier() == null && resposta != null) {
            return Response
                    .created(URI.create("/alunos/" + resposta.toString()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @DELETE
    @Path("/{identifierAluno}/{identifierSemestre}")
    public Response removerPorIdentifier(@PathParam("identifierAluno") Long identifierAluno,
                                         @PathParam("identifierSemestre") Long identifierSemestre) {
        boolean removido = service.excluirPorIdentifier(identifierAluno, identifierSemestre);
        if (!removido) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return Response.noContent().build();
    }
}
