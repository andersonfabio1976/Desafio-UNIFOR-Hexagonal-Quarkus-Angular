package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.dto.UsuarioDTO;
import br.com.unifor.adapters.integration.UsuarioService;
import br.com.unifor.adapters.mapper.AlunoMapper;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import br.com.unifor.application.service.AlunoService;
import br.com.unifor.domain.model.Aluno;
import jakarta.annotation.security.PermitAll;
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
@PermitAll
public class AlunoController {

    @Inject
    AlunoService service;

    @Inject
    UsuarioService usuarioService;

    @Inject
    AlunoMapper mapper;

    @Inject
    UsuarioMapper usuarioMapper;

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
        UsuarioDTO usuarioCriado = usuarioService.criarUsuarioComKeycloak(dto.getUsuario(), "ALUNO");
        usuarioCriado = usuarioMapper.toDTO(usuarioService.buscarPorUserNameRealm(usuarioCriado.getUsername()).get());
        dto.setUsuario(usuarioCriado);
        Aluno aluno = mapper.toDomainFromDTO(dto);
        service.salvar(aluno);
        AlunoDTO resposta = mapper.toDTO(aluno);

        if (dto.getIdentifier() == null && resposta.getIdentifier() != null) {
            return Response
                    .created(URI.create("/alunos/" + resposta.getIdentifier()))
                    .entity(resposta)
                    .build();
        }
        return Response.ok(resposta).build();
    }

    @PUT
    @Path("/{identifier}")
    @Transactional
    public Response atualizar(@PathParam("identifier") Long identifier, AlunoDTO dto) {
        usuarioService.atualizarUsuario(dto.getUsuario(), dto.getUsuario().getIdentifier());
        service.atualizar(mapper.toDomainFromDTO(dto), identifier);
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        Aluno aluno = service.buscarPorIdentifier(identifier).get();
        usuarioService.excluirUsuario(aluno.getUsuario().getIdentifier());
        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return Response.noContent().build();
    }
}