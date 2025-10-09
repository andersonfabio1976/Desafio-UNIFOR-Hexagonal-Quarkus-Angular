import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class AppAuthGuard extends KeycloakAuthGuard {
  constructor(protected override router: Router, protected override keycloakAngular: KeycloakService) {
    super(router, keycloakAngular);
  }

  async isAccessAllowed(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot) {
    // garante que está autenticado
    if (!this.authenticated) {
      await this.keycloakAngular.login();
      return false;
    }
    const requiredRoles = route.data['roles'] as string[] | undefined;
    if (!requiredRoles || requiredRoles.length === 0) return true;

    // verifica se o usuário possui pelo menos 1 das roles exigidas
    return requiredRoles.some((role) => this.roles.includes(role));
  }
}
