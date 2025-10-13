package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.AlunoDTO;
import br.com.unifor.adapters.mapper.AlunoMapper;
import br.com.unifor.application.service.AlunoService;
import br.com.unifor.domain.model.Aluno;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ROLE_ADMIN", "ROLE_ADMINISTRADOR"})
public class AlunoController {

    @Inject
    AlunoService servico;

    @Inject
    AlunoMapper mapper;

    @GET
    public List<AlunoDTO> listarTodos() {
        return mapper.toDTO(servico.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public AlunoDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return servico.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
    }

    @POST
    @Transactional
    public void salvar(AlunoDTO dto) {
        Aluno aluno = mapper.toDomainFromEntity(mapper.toEntity(mapper.toDomainFromDTO(dto)));
        servico.salvar(aluno);
    }

    @DELETE
    @Path("/{identifier}")
    @Transactional
    public boolean removerPorIdentifier(@PathParam("identifier") Long identifier) {
        return servico.excluirPorIdentifier(identifier);
    }
}
