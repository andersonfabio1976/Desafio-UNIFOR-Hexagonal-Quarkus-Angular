package br.com.unifor.adapters.repository.impl;

import br.com.unifor.adapters.dto.DisciplinaDTO;
import br.com.unifor.adapters.dto.MatrizItemDTO;
import br.com.unifor.adapters.mapper.DisciplinaMapper;
import br.com.unifor.adapters.repository.entity.DisciplinaEntity;
import br.com.unifor.application.port.repository.DisciplinaRepositoryPort;
import br.com.unifor.domain.model.Disciplina;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DisciplinaRepositoryAdapter
        extends BaseRepositoryAdapter<DisciplinaEntity, Disciplina>
        implements DisciplinaRepositoryPort {

    private final DisciplinaMapper mapper;

    @jakarta.inject.Inject
    public DisciplinaRepositoryAdapter(DisciplinaMapper mapper) {
        super(mapper::toDomainFromEntity, mapper::toEntity, mapper::toUpdateEntityMapper);
        this.mapper = mapper;
    }

    public List<MatrizItemDTO> listMatrizByCurso(Long cursoId) {
        // Constructor expression diretamente no find() do Panache
        return find("""
                select new br.com.unifor.adapters.dto.MatrizItemDTO(
                    s.numero,
                    d.nome,
                    d.cargaHoraria,
                    coalesce(p.nome, 'Departamento')
                )
                from DisciplinaEntity d
                  left join d.professor p
                  left join d.semestre s
                where d.curso.identifier = :cursoId
                order by s.numero asc, d.nome asc
                """,
                Parameters.with("cursoId", cursoId)
        ).project(MatrizItemDTO.class).list();
        // Observação: nas versões atuais do Panache, o retorno tipado para DTO funciona
        // com constructor expression. Se sua versão não suportar project() aqui,
        // troque por getEntityManager().createQuery(..., MatrizItemDTO.class).getResultList().
    }

    public List<MatrizItemDTO> listMatrizByProfessor(Long professorId) {
        return find("""
                select new br.com.unifor.adapters.dto.MatrizItemDTO(
                    s.numero,
                    d.nome,
                    d.cargaHoraria,
                    coalesce(p.nome, 'Departamento')
                )
                from DisciplinaEntity d
                  left join d.professor p
                  left join d.semestre s
                where p.identifier = :profId
                order by s.numero asc, d.nome asc
                """,
                Parameters.with("profId", professorId)
        ).project(MatrizItemDTO.class).list();
    }

}