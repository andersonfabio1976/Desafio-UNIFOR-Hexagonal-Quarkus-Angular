import { KeycloakService } from 'keycloak-angular';

// Ajuste estes valores ao seu Keycloak
const KEYCLOAK_URL = 'http://localhost:8080';      // ou http://unifor-keycloak:8080 quando frontend estiver no container
const REALM = 'unifor-realm';
const CLIENT_ID = 'unifor-frontend';

export function initializeKeycloak(keycloak: KeycloakService) {
  return async () => {
    await keycloak.init({
      config: {
        url: KEYCLOAK_URL,
        realm: REALM,
        clientId: CLIENT_ID
      },
      initOptions: {
        onLoad: 'login-required',
        pkceMethod: 'S256',
      },
      bearerExcludedUrls: [
        '/assets',
      ],
      enableBearerInterceptor: true,
      bearerPrefix: 'Bearer'
    });

  };
}
