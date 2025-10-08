package br.com.unifor.adapters.rest;

import br.com.unifor.domain.model.Aluno;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/alunos")
public class AlunoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno exemplo() {
        return new Aluno(1000L, "Anderson Fábio", "2025A01");
    }
}
