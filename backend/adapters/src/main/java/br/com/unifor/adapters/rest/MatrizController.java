package br.com.unifor.adapters.rest;

import br.com.unifor.adapters.dto.MatrizItemDTO;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/Matriz")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatrizController {

    @Inject
    EntityManager em;

    // Ex.: GET /Matriz?cursoId=1
    @GET
    public List<MatrizItemDTO> listar(@QueryParam("cursoId") Long cursoId) {
        if (cursoId == null) {
            throw new BadRequestException("Parâmetro cursoId é obrigatório");
        }
        String jpql = """
            SELECT new br.com.unifor.adapters.dto.MatrizItemDTO(
                s.numero,
                d.nome,
                d.cargaHoraria,
                COALESCE(p.nome, 'Departamento')
            )
            FROM DisciplinaEntity d
              LEFT JOIN d.professor p
              LEFT JOIN d.semestre s
            WHERE d.curso.identifier = :cursoId
            ORDER BY s.numero ASC, d.nome ASC
            """;
        return em.createQuery(jpql, MatrizItemDTO.class)
                .setParameter("cursoId", cursoId)
                .getResultList();
    }
}