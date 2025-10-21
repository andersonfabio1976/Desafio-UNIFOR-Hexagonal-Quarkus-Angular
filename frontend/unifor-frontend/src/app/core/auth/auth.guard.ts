import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private keycloak: KeycloakService, private router: Router) {}

  async canActivate(): Promise<boolean> {
    const isLoggedIn = await this.keycloak.isLoggedIn();
    if (!isLoggedIn) {
      await this.keycloak.login();
      return false;
    }

    const userRoles = this.keycloak.getUserRoles();
    const hasAccess = userRoles.includes('ADMIN') ||
      userRoles.includes('COORDENADOR') ||
    userRoles.includes('PROFESSOR') ||
    userRoles.includes('ALUNO');

    if (!hasAccess) {
      console.warn('Acesso negado: usuário sem permissão de administrador.');
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }
}
