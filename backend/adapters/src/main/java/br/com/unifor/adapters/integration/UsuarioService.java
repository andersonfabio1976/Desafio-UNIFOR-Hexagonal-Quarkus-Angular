package br.com.unifor.adapters.integration;

import br.com.unifor.adapters.dto.UsuarioDTO;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import br.com.unifor.adapters.config.KeycloakAdminClientProvider;
import br.com.unifor.domain.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    KeycloakAdminClientProvider kcProvider;

    @Transactional
    public UsuarioDTO criarUsuarioComKeycloak(UsuarioDTO dto, String rule) {
        var kc = kcProvider.getKeycloak();
        var realm = kcProvider.getTargetRealm();
        var realmResource = kc.realm(realm);
        var usersResource = realmResource.users();

        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setEnabled(true);

        var response = usersResource.create(user);
        if (response.getStatus() != 201)
            throw new RuntimeException("Falha ao criar usuário no Keycloak: " + response.getStatus());

        String keycloakId = response.getLocation().toString()
                .substring(response.getLocation().toString().lastIndexOf("/") + 1);

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(dto.getPassword());
        cred.setTemporary(false);
        usersResource.get(keycloakId).resetPassword(cred);

        RoleRepresentation roleRepresentation = realmResource.roles().get(rule).toRepresentation();
        usersResource.get(keycloakId).roles().realmLevel().add(List.of(roleRepresentation));

        dto.setKeycloakIdentifier(keycloakId);

        // ✅ Agora usa o repository injetado
        usuarioRepository.salvar(usuarioMapper.toDomainFromDTO(dto));

        return dto;
    }

    @Transactional
    public void atualizarUsuario(UsuarioDTO dto, Long identifier) {
        Usuario usuario = usuarioRepository.buscarPorIdentifier(identifier)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        var kc = kcProvider.getKeycloak();
        String realm = kcProvider.getTargetRealm();
        var userResource = kc.realm(realm).users().get(usuario.getKeycloakIdentifier());

        UserRepresentation user = new UserRepresentation();
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        userResource.update(user);

        if (dto.getPassword() != null) {
            CredentialRepresentation cred = new CredentialRepresentation();
            cred.setType(CredentialRepresentation.PASSWORD);
            cred.setValue(dto.getPassword());
            cred.setTemporary(false);
            userResource.resetPassword(cred);
        }

        usuarioRepository.atualizar(usuario, identifier);
    }

    @Transactional
    public void excluirUsuario(Long identifier) {
        Usuario usuario = usuarioRepository.buscarPorIdentifier(identifier)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        var kc = kcProvider.getKeycloak();
        String realm = kcProvider.getTargetRealm();
        kc.realm(realm).users().delete(usuario.getKeycloakIdentifier());

        usuarioRepository.excluirPorIdentifier(identifier);
    }

    public Optional<Usuario> buscarPorUserNameRealm(String userName) {
        return usuarioRepository.buscarPorUserName(userName);
    }

    public Optional<Usuario> buscarPorEmailRealm(String email) {
        return usuarioRepository.buscarPorEmail(email);
    }
}
