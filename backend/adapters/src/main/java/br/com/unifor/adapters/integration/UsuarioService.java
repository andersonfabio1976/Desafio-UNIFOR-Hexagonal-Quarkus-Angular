package br.com.unifor.adapters.integration;

import br.com.unifor.adapters.config.KeycloakAdminClientProvider;
import br.com.unifor.adapters.dto.UsuarioDTO;
import br.com.unifor.adapters.mapper.UsuarioMapper;
import br.com.unifor.domain.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioService {

    private static final Logger LOG = Logger.getLogger(UsuarioService.class);

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
        if (response.getStatus() != 201) {
            throw new RuntimeException("Falha ao criar usuário no Keycloak: " + response.getStatus());
        }

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

        String kcId = safe(usuario.getKeycloakIdentifier());
        var kc = kcProvider.getKeycloak();
        String realm = kcProvider.getTargetRealm();
        UsersResource users = kc.realm(realm).users();


        if (kcId.isBlank()) {
            String username = safe(usuario.getUsername());
            String email = safe(usuario.getEmail());

            if (!username.isBlank()) {
                kcId = tryResolveKeycloakIdBySearch(users, username, username);
            }
            if (kcId.isBlank() && !email.isBlank()) {
                kcId = tryResolveKeycloakIdBySearch(users, email, username);
            }
        }

        if (!kcId.isBlank()) {
            try {
                users.get(kcId).remove();
            } catch (Exception e) {
                LOG.warnf(e, "Falha ao remover usuário no Keycloak (kcId=%s). Prosseguindo com remoção no banco.", kcId);
            }
        } else {
            LOG.warnf("Keycloak ID não localizado (identifier=%s, username=%s, email=%s). Removendo apenas no banco.",
                    identifier, usuario.getUsername(), usuario.getEmail());
        }

        usuarioRepository.excluirPorIdentifier(identifier);
    }


    public Optional<Usuario> buscarPorUserNameRealm(String userName) {
        return usuarioRepository.buscarPorUserName(userName);
    }

    public Optional<Usuario> buscarPorEmailRealm(String email) {
        return usuarioRepository.buscarPorEmail(email);
    }

    // ====== HELPERS ======

    private static String tryResolveKeycloakIdBySearch(UsersResource users, String search, String usernameToMatch) {
        try {
            List<UserRepresentation> found = users.search(search, 0, 10);
            if (found != null && !found.isEmpty()) {
                if (usernameToMatch != null && !usernameToMatch.isBlank()) {
                    for (UserRepresentation ur : found) {
                        if (ur.getUsername() != null && ur.getUsername().equalsIgnoreCase(usernameToMatch)) {
                            return ur.getId();
                        }
                    }
                }
                return found.get(0).getId();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioService.class).warnf(e, "Falha ao buscar usuário no Keycloak por '%s'", search);
        }
        return "";
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }
}