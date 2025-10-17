package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.dto.UsuarioDTO;
import br.com.unifor.adapters.integration.UsuarioService;
import br.com.unifor.adapters.mapper.ProfessorMapper;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import br.com.unifor.adapters.repository.entity.ProfessorEntity;
import br.com.unifor.adapters.repository.impl.ProfessorRepositoryAdapter;
import br.com.unifor.application.service.ProfessorService;
import br.com.unifor.domain.model.Professor;
import br.com.unifor.domain.model.Usuario;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class ProfessorController {

    @Inject
    ProfessorService service;

    @Inject
    UsuarioService usuarioService;

    @Inject
    ProfessorMapper mapper;

    @Inject
    UsuarioMapper usuarioMapper;
    @Inject
    ProfessorRepositoryAdapter professorRepositoryAdapter;

    @GET
    public List<ProfessorDTO> listarTodos() {
        return mapper.toListDTO(service.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public ProfessorDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return service.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));
    }

    @POST
    @Transactional
    public Response salvar(ProfessorDTO dto) {
        UsuarioDTO usuarioCriado = usuarioService.criarUsuarioComKeycloak(dto.getUsuario(),dto.getCoordenador() ? "COORDENADOR" : "PROFESSOR");
        usuarioCriado = usuarioMapper.toDTO(usuarioService.buscarPorUserNameRealm(usuarioCriado.getUsername()).get());
        dto.setUsuario(usuarioCriado);
        Professor dominio = mapper.toDomainFromDTO(dto);
        service.salvar(dominio);
        ProfessorDTO resposta = mapper.toDTO(dominio);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/professores/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @PUT
    @Path("/{identifier}")
    @Transactional
    public Response atualizar(@PathParam("identifier") Long identifier, ProfessorDTO dto) {
        usuarioService.atualizarUsuario(dto.getUsuario(), dto.getUsuario().getIdentifier());
        service.atualizar(mapper.toDomainFromDTO(dto), identifier);
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        Professor professor = service.buscarPorIdentifier(identifier).get();
        usuarioService.excluirUsuario(professor.getUsuario().getIdentifier());
        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Professor não encontrado");
        }
        return Response.noContent().build();
    }
}