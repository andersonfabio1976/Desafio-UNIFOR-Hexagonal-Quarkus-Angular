import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class RoleGuard extends KeycloakAuthGuard {
  constructor(protected override router: Router, protected override keycloakAngular: KeycloakService) {
    super(router, keycloakAngular);
  }

  async isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean | UrlTree> {
    // 1) precisa estar logado
    if (!this.authenticated) {
      await this.keycloakAngular.login({ redirectUri: window.location.href });
      return false;
    }

    // 2) verifica roles esperadas
    const expectedRoles: string[] = route.data['roles'] ?? [];
    if (expectedRoles.length === 0) return true;

    const userRoles = this.roles; // do KeycloakAuthGuard
    const ok = expectedRoles.some((r) => userRoles.includes(r));
    if (!ok) {
      // Redireciona para uma página de acesso negado
      return this.router.parseUrl('/acesso-negado');
    }
    return true;
  }
}
