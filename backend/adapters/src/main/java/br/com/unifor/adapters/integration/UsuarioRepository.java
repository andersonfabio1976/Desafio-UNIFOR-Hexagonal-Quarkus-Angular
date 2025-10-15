package br.com.unifor.adapters.integration;

import br.com.unifor.adapters.repository.entity.UsuarioEntity;
import br.com.unifor.domain.model.Usuario;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioEntity> {

    @Inject
    UsuarioMapper usuarioMapper;

    /**
     * 🔍 Busca usuário pelo ID (identifier)
     */
    public Optional<Usuario> buscarPorIdentifier(Long identifier) {
        return findByIdOptional(identifier)
                .map(usuarioMapper::toDomainFromEntity);
    }

    /**
     * 📋 Lista todos os usuários convertendo para o domínio
     */
    public List<Usuario> listarTodos() {
        return listAll()
                .stream()
                .map(usuarioMapper::toDomainFromEntity)
                .toList();
    }

    /**
     * 💾 Persiste um novo usuário no banco
     */
    @Transactional
    public void salvar(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        persist(entity);
    }

    /**
     * ✏️ Atualiza um usuário existente
     */
    @Transactional
    public void atualizar(Usuario usuario, Long identifier) {
        UsuarioEntity entity = findByIdOptional(identifier)
                .orElseThrow(() -> new NotFoundException("Registro não encontrado"));
        usuarioMapper.toUpdateEntityMapper(usuario, entity);
        persist(entity);
    }

    /**
     * ❌ Exclui um usuário pelo identifier
     */
    @Transactional
    public boolean excluirPorIdentifier(Long identifier) {
        return deleteById(identifier);
    }

    /**
     * 🔍 Busca usuário pelo username
     */
    public Optional<Usuario> buscarPorUserName(String username) {
        return find("username", username)
                .firstResultOptional()
                .map(usuarioMapper::toDomainFromEntity);
    }

    /**
     * 🔍 Busca usuário pelo email
     */
    public Optional<Usuario> buscarPorEmail(String email) {
        return find("email", email)
                .firstResultOptional()
                .map(usuarioMapper::toDomainFromEntity);
    }
}
