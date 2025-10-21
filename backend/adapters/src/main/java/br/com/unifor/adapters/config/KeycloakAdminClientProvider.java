package br.com.unifor.adapters.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

@ApplicationScoped
public class KeycloakAdminClientProvider {

    @ConfigProperty(name = "keycloak.server-url")
    String serverUrl;

    @ConfigProperty(name = "keycloak.admin-realm", defaultValue = "master")
    String adminRealm;

    @ConfigProperty(name = "keycloak.realm")
    String targetRealm;

    @ConfigProperty(name = "keycloak.client-id", defaultValue = "admin-cli")
    String clientId;

    @ConfigProperty(name = "keycloak.username")
    String username;

    @ConfigProperty(name = "keycloak.password")
    String password;

    public Keycloak getKeycloak() {
        if (isBlank(serverUrl) || isBlank(clientId) || isBlank(username) || isBlank(password)) {
            throw new IllegalStateException("Config do Keycloak incompleta: keycloak.server-url/client-id/username/password");
        }
        if (isBlank(adminRealm)) {
            throw new IllegalStateException("Config ausente: keycloak.admin-realm");
        }
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(adminRealm)
                .clientId(clientId)
                .username(username)
                .password(password)
                .build();
    }

    public String getTargetRealm() {
        if (isBlank(targetRealm)) {
            throw new IllegalStateException("Config ausente: keycloak.realm (realm alvo)");
        }
        return targetRealm;
    }

    public String getAdminRealm() {
        return adminRealm;
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}