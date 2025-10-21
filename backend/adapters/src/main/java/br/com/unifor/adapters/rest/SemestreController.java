package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.SemestreDTO;
import br.com.unifor.adapters.mapper.SemestreMapper;
import br.com.unifor.application.service.SemestreService;
import br.com.unifor.domain.model.Semestre;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/Semestres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class SemestreController {

    @Inject
    SemestreService service;

    @Inject
    SemestreMapper mapper;

    @GET
    public List<SemestreDTO> listarTodos() {
        return mapper.toListDTO(service.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public SemestreDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return service.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Semestre não encontrado"));
    }

    @POST
    @Transactional
    public Response salvar(SemestreDTO dto) {
        Semestre dominio = mapper.toDomainFromDTO(dto);
        service.salvar(dominio);
        SemestreDTO resposta = mapper.toDTO(dominio);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/Semestres/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @PUT
    @Path("/{identifier}")
    @Transactional
    public Response atualizar(@PathParam("identifier") Long identifier, SemestreDTO dto) {
        service.atualizar(mapper.toDomainFromDTO(dto), identifier);
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Semestre não encontrado");
        }
        return Response.noContent().build();
    }
}