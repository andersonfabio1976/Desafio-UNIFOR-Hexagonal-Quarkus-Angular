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

    /** Busca usuário pelo ID (identifier) — null-safe */
    public Optional<Usuario> buscarPorIdentifier(Long identifier) {
        if (identifier == null) {
            return Optional.empty(); // evita findByIdOptional(null) -> IllegalArgumentException
        }
        return findByIdOptional(identifier).map(usuarioMapper::toDomainFromEntity);
    }

    /** Lista todos os usuários convertendo para o domínio */
    public List<Usuario> listarTodos() {
        return listAll()
                .stream()
                .map(usuarioMapper::toDomainFromEntity)
                .toList();
    }

    /** Persiste um novo usuário no banco */
    public void salvar(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        persist(entity);
    }

    /** Atualiza um usuário existente */
    public void atualizar(Usuario usuario, Long identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Identifier do usuário é obrigatório para atualizar");
        }
        UsuarioEntity entity = findByIdOptional(identifier)
                .orElseThrow(() -> new jakarta.ws.rs.NotFoundException("Registro não encontrado"));
        usuarioMapper.toUpdateEntityMapper(usuario, entity);
        persist(entity);
    }

    /** Exclui um usuário pelo identifier */
    public boolean excluirPorIdentifier(Long identifier) {
        if (identifier == null) return false;
        return deleteById(identifier);
    }

    /** Busca usuário pelo username */
    public Optional<Usuario> buscarPorUserName(String username) {
        if (username == null || username.isBlank()) return Optional.empty();
        return find("lower(username) = ?1", username.toLowerCase())
                .firstResultOptional()
                .map(usuarioMapper::toDomainFromEntity);
    }

    /** Busca usuário pelo email */
    public Optional<Usuario> buscarPorEmail(String email) {
        if (email == null || email.isBlank()) return Optional.empty();
        return find("lower(email) = ?1", email.toLowerCase())
                .firstResultOptional()
                .map(usuarioMapper::toDomainFromEntity);
    }
}