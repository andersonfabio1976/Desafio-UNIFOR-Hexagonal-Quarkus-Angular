package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.mapper.AlunoMapper;
import br.com.unifor.application.service.AlunoService;
import br.com.unifor.domain.model.Aluno;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"ADMIN", "ROLE_ADMIN", "ROLE_ADMINISTRADOR"})
public class AlunoController {

    @Inject
    AlunoService service;

    @Inject
    AlunoMapper mapper;

    @GET
    public List<AlunoDTO> listarTodos() {
        return mapper.toListDTO(service.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public AlunoDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return service.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
    }

    @POST
    @Transactional
    public Response salvar(AlunoDTO dto) {
        Aluno dominio = mapper.toDomainFromDTO(dto);
        service.salvar(dominio);
        AlunoDTO resposta = mapper.toDTO(dominio);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/alunos/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return Response.noContent().build();
    }
}