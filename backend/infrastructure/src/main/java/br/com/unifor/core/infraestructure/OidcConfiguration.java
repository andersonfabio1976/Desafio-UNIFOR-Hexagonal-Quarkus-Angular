package br.com.unifor.core.infraestructure;

//import io.smallrye.config.ConfigMapping;
//import io.smallrye.config.WithName;
//import io.quarkus.arc.properties.IfBuildProperty;
//import jakarta.enterprise.context.ApplicationScoped;
//@ConfigMapping(prefix = "quarkus.oidc")
//@ApplicationScoped
//@IfBuildProperty(name = "app.security.enabled", stringValue = "true", enableIfMissing = false)
//public interface OidcConfiguration {
//    @WithName("auth-server-url")
//    String authServerUrl();
//
//    @WithName("client-id")
//    String clientId();
//
//    @WithName("application-type")
//    String applicationType();
//
//    Credentials credentials();
//
//    interface Credentials {
//        String secret();
//    }
//}