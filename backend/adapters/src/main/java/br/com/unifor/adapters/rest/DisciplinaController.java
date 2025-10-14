package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.mapper.DisciplinaMapper;
import br.com.unifor.application.service.DisciplinaService;
import br.com.unifor.domain.model.Disciplina;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/Disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"ADMIN", "ROLE_ADMINISTRADOR"})
public class DisciplinaController {

    @Inject
    DisciplinaService service;

    @Inject
    DisciplinaMapper mapper;

    @GET
    @Path("/{identifierCurso}")
    public List<DisciplinaDTO> listarTodos(@PathParam("identifierCurso") Long identifierCurso) {
        return mapper.toListDTO(service.listarPorCurso(identifierCurso));
    }

    @GET
    @Path("/{identifierCurso}/{identifierDisciplina}")
    public DisciplinaDTO buscarPorIdentifier(@PathParam("identifierCurso") Long identifierCurso,
                                             @PathParam("identifierDisciplina") Long identifierDisciplina) {
        return service.buscarPorIdentifier(identifierCurso, identifierDisciplina)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrado"));
    }

    @POST
    @Transactional
    @Path("/{identifierCurso}")
    public Response salvar(@PathParam("identifierCurso") Long identifierCurso, DisciplinaDTO dto) {
        Disciplina dominio = mapper.toDomainFromDTO(dto);
        service.salvar(identifierCurso, dominio);
        DisciplinaDTO resposta = mapper.toDTO(dominio);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/Disciplinas/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @DELETE
    @Path("/{identifierCurso}/{identifierDisciplina}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifierCurso") Long identifierCurso,
                                        @PathParam("identifierDisciplina") Long identifierDisciplina) {
        boolean removido = service.excluirPorIdentifier(identifierCurso , identifierDisciplina);
        if (!removido) {
            throw new NotFoundException("Disciplina não encontrado");
        }
        return Response.noContent().build();
    }
}