package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.ProfessorDTO;
import br.com.unifor.adapters.mapper.ProfessorMapper;
import br.com.unifor.application.service.ProfessorService;
import br.com.unifor.domain.model.Professor;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ROLE_ADMIN", "ROLE_ADMINISTRADOR"})
public class ProfessorController {

    @Inject
    ProfessorService servico;

    @Inject
    ProfessorMapper mapper;


    @GET
    public List<ProfessorDTO> listarTodos() {
        return mapper.toDTO(servico.listarTodos());
    }

    @GET
    @Path("/{identifier}")
    public ProfessorDTO buscarPorIdentifier(@PathParam("identifier") Long identifier) {
        return servico.buscarPorIdentifier(identifier)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));
    }

    @POST
    @Transactional
    public void salvar(ProfessorDTO dto) {
        Professor professor = mapper.toDomain(mapper.toEntity(mapper.toDomain(dto)));
        servico.salvar(professor);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public boolean removerPorIdentifier(@PathParam("identifier") Long identifier) {
        return servico.excluirPorIdentifier(identifier);
    }
}
