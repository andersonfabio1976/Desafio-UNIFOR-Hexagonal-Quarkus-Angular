import { Injectable } from '@angular/core';
import {
  CanActivate,
  CanActivateChild,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate, CanActivateChild {
  constructor(private keycloakService: KeycloakService, private router: Router) {}

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    const hasAccess = await this.checkAccess(route);
    if (!hasAccess) {
      this.router.navigate(['/acesso-negado']);
    }
    return hasAccess;
  }

  async canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    return this.canActivate(route, state);
  }

  private async checkAccess(route: ActivatedRouteSnapshot): Promise<boolean> {
    const roles = route.data['roles'] as Array<string>;
    const isLoggedIn = await this.keycloakService.isLoggedIn();

    if (!isLoggedIn) {
      await this.keycloakService.login();
      return false;
    }

    if (!roles || roles.length === 0) {
      return true;
    }

    return roles.some((role) => this.keycloakService.isUserInRole(role));
  }
}
