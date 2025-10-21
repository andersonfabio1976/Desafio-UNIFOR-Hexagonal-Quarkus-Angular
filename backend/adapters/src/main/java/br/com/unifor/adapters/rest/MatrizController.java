package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.MatrizAlunoResponseDTO;
import br.com.unifor.adapters.dto.MatrizItemDTO;
import br.com.unifor.adapters.dto.MatrizProfessorResponseDTO;
import br.com.unifor.adapters.repository.impl.DisciplinaRepositoryAdapter;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/matriz")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class MatrizController {

    @Inject
    DisciplinaRepositoryAdapter disciplinaRepository;

    @GET
    @Path("/aluno")
    public MatrizAlunoResponseDTO listarAluno(@QueryParam("cursoIdentifier") Long cursoId) {
        if (cursoId == null) {
            throw new BadRequestException("Parâmetro cursoId é obrigatório");
        }
        List<MatrizItemDTO> itens = disciplinaRepository.listMatrizByCurso(cursoId);
        return new MatrizAlunoResponseDTO(cursoId, itens);
    }

    @GET
    @Path("/professor")
    public MatrizProfessorResponseDTO listarProfessor(@QueryParam("professorIdentifier") Long professorId) {
        if (professorId == null) {
            throw new BadRequestException("Parâmetro professorId é obrigatório");
        }
        List<MatrizItemDTO> itens = disciplinaRepository.listMatrizByProfessor(professorId);
        return new MatrizProfessorResponseDTO(itens);
    }

    @GET
    @Path("/legacy")
    public List<MatrizItemDTO> listarLegacy(@QueryParam("cursoIdentifier") Long cursoId) {
        if (cursoId == null) {
            throw new BadRequestException("Parâmetro cursoId é obrigatório");
        }
        return disciplinaRepository.listMatrizByCurso(cursoId);
    }
}