package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.dto.UsuarioDTO;
import br.com.unifor.adapters.integration.UsuarioService;
import br.com.unifor.adapters.mapper.AlunoMapper;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import br.com.unifor.application.service.AlunoService;
import br.com.unifor.application.service.CursoService;
import br.com.unifor.domain.model.Aluno;
import br.com.unifor.domain.model.Curso;
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
@PermitAll
public class AlunoController {

    @Inject
    AlunoService service;

    @Inject
    CursoService cursoService;

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
        usuarioCriado = usuarioMapper.toDTO(
                usuarioService.buscarPorUserNameRealm(usuarioCriado.getUsername()).orElseThrow()
        );
        dto.setUsuario(usuarioCriado);
        Aluno aluno = mapper.toDomainFromDTO(dto);

        Long cursoId = resolveCursoId(dto);
        if (cursoId != null) {
            Curso curso = cursoService.buscarPorIdentifier(cursoId)
                    .orElseThrow(() -> new NotFoundException("Curso não encontrado: " + cursoId));
            aluno.setCurso(curso);
        }
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
        Aluno atual = service.buscarPorIdentifier(identifier)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado: " + identifier));
        if (dto.getUsuario() != null) {
            if (dto.getUsuario().getIdentifier() == null && atual.getUsuario() != null) {
                dto.getUsuario().setIdentifier(atual.getUsuario().getIdentifier());
            }
            usuarioService.atualizarUsuario(dto.getUsuario(), dto.getUsuario().getIdentifier());
        }

        Aluno atualizado = mapper.toDomainFromDTO(dto);

        Long cursoId = resolveCursoId(dto);
        if (cursoId != null) {
            Curso curso = cursoService.buscarPorIdentifier(cursoId)
                    .orElseThrow(() -> new NotFoundException("Curso não encontrado: " + cursoId));
            atualizado.setCurso(curso);
        } else {
            atualizado.setCurso(atual.getCurso());
        }

        service.atualizar(atualizado, identifier);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public Response removerPorIdentifier(@PathParam("identifier") Long identifier) {
        Aluno aluno = service.buscarPorIdentifier(identifier)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (aluno.getUsuario() != null && aluno.getUsuario().getIdentifier() != null) {
            usuarioService.excluirUsuario(aluno.getUsuario().getIdentifier());
        }

        boolean removido = service.excluirPorIdentifier(identifier);
        if (!removido) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return Response.noContent().build();
    }

    private Long resolveCursoId(AlunoDTO dto) {
        if (dto == null) return null;
        try {
            if (dto.getCurso() != null && dto.getCurso().getIdentifier() != null) {
                return dto.getCurso().getIdentifier();
            }
        } catch (Exception ignored) {}

        try {
            var m = dto.getClass().getMethod("getCursoIdentifier");
            Object v = m.invoke(dto);
            if (v instanceof Long) return (Long) v;
            if (v instanceof Number) return ((Number) v).longValue();
        } catch (Exception ignored) {}

        return null;
    }
}