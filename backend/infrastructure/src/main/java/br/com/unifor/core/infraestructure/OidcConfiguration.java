package br.com.unifor.core.infraestructure;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperties;

@Getter
@Setter
@ApplicationScoped
@ConfigProperties(prefix = "quarkus.oidc")
public class OidcConfiguration {
    private String authServerUrl;
    private String clientId;
    private String credentialsSecret;
    private String applicationType;
}
