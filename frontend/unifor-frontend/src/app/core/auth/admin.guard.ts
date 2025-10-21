import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private keycloakService: KeycloakService, private router: Router) {}

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    const isLoggedIn = await this.keycloakService.isLoggedIn();

    if (!isLoggedIn) {
      await this.keycloakService.login({
        redirectUri: window.location.origin + state.url,
      });
      return false;
    }

    const roles = this.keycloakService.getUserRoles();
    const adminOk = roles.includes('ADMIN') || roles.includes('ROLE_ADMIN');
    if (adminOk) return true;

    // ALTERAÇÃO: se for coordenador, redireciona ao espaço dele; senão acesso negado
    const isCoord = roles.includes('COORDENADOR') || roles.includes('ROLE_COORDENADOR');
    if (isCoord) return this.router.parseUrl('/coord');

    return this.router.parseUrl('/acesso-negado');
  }
}
