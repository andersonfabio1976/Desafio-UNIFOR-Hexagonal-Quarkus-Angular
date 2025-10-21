import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  UrlTree,
} from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class CoordenadorGuard implements CanActivate, CanActivateChild {
  constructor(private keycloak: KeycloakService, private router: Router) {}

  async canActivate(route: ActivatedRouteSnapshot): Promise<boolean | UrlTree> {
    return this.checkRole();
  }

  async canActivateChild(route: ActivatedRouteSnapshot): Promise<boolean | UrlTree> {
    return this.checkRole();
  }

  private async checkRole(): Promise<boolean | UrlTree> {
    const logged = await this.keycloak.isLoggedIn();
    if (!logged) {
      await this.keycloak.login({ redirectUri: window.location.href });
      return false;
    }

    const roles = this.keycloak.getUserRoles();
    const isCoord = roles.includes('COORDENADOR') || roles.includes('ROLE_COORDENADOR');
    if (isCoord) return true;

    // ALTERAÇÃO: se for admin, manda para admin; senão acesso negado
    const isAdmin = roles.includes('ADMIN') || roles.includes('ROLE_ADMIN');
    if (isAdmin) return this.router.parseUrl('/admin');

    return this.router.parseUrl('/acesso-negado');
  }
}
