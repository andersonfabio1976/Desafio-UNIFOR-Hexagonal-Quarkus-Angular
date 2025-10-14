// src/app/core/auth/keycloack-init.factory.ts
import { KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService) {
  return async () => {
    await keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'unifor-realm',
        clientId: 'unifor-frontend',
      },
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: false,
        pkceMethod: 'S256',
      },
      enableBearerInterceptor: true,
      bearerExcludedUrls: ['/assets'],
    });
  };
}
