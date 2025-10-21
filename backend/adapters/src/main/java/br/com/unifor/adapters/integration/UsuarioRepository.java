package br.com.unifor.adapters.integration;

import br.com.unifor.adapters.repository.entity.UsuarioEntity;
import br.com.unifor.domain.model.Usuario;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioEntity> {

    @Inject
    UsuarioMapper usuarioMapper;

    public Optional<Usuario> buscarPorIdentifier(Long identifier) {
        if (identifier == null) {
            return Optional.empty();
        }
        return findByIdOptional(identifier).map(usuarioMapper::toDomainFromEntity);
    }

    public List<Usuario> listarTodos() {
        return listAll()
                .stream()
                .map(usuarioMapper::toDomainFromEntity)
                .toList();
    }

    public void salvar(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        persist(entity);
    }

    public void atualizar(Usuario usuario, Long identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Identifier do usuário é obrigatório para atualizar");
        }
        UsuarioEntity entity = findByIdOptional(identifier)
                .orElseThrow(() -> new jakarta.ws.rs.NotFoundException("Registro não encontrado"));
        usuarioMapper.toUpdateEntityMapper(usuario, entity);
        persist(entity);
    }

    public boolean excluirPorIdentifier(Long identifier) {
        if (identifier == null) return false;
        return deleteById(identifier);
    }

    public Optional<Usuario> buscarPorUserName(String username) {
        if (username == null || username.isBlank()) return Optional.empty();
        return find("lower(username) = ?1", username.toLowerCase())
                .firstResultOptional()
                .map(usuarioMapper::toDomainFromEntity);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        if (email == null || email.isBlank()) return Optional.empty();
        return find("lower(email) = ?1", email.toLowerCase())
                .firstResultOptional()
                .map(usuarioMapper::toDomainFromEntity);
    }
}