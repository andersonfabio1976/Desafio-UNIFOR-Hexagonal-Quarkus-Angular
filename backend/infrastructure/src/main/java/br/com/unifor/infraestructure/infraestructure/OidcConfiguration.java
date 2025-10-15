package br.com.unifor.infraestructure.infraestructure;

import io.smallrye.config.ConfigMapping;
import jakarta.enterprise.context.ApplicationScoped;

@ConfigMapping(prefix = "quarkus.oidc")
@ApplicationScoped
public interface OidcConfiguration {
    String authServerUrl();
    String clientId();
    String credentialsSecret();
    String applicationType();
}
