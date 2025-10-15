import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
} from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class CoordenadorGuard implements CanActivate, CanActivateChild {
  constructor(private keycloak: KeycloakService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    return this.checkRole(route);
  }

  canActivateChild(route: ActivatedRouteSnapshot): boolean {
    return this.checkRole(route);
  }

  private checkRole(route: ActivatedRouteSnapshot): boolean {
    const requiredRoles = route.data['roles'];
    const userRoles = this.keycloak.getUserRoles();
    const hasAccess = requiredRoles.some((role: string) =>
      userRoles.includes(role)
    );
    if (!hasAccess) this.router.navigate(['/']);
    return hasAccess;
  }
}
