import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private keycloak: KeycloakService) {}

  async initKeycloak(): Promise<void> {
    await this.keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'unifor-realm',
        clientId: 'unifor-backend'
      },
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: false
      }
    });
  }
}
