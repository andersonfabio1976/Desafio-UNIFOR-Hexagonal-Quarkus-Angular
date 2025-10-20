import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  // Standalone para não precisar mexer em módulos
  standalone: true,
  selector: 'app-role-landing',
  template: `<p style="margin:16px">Redirecionando...</p>`,
})
export class RoleLandingComponent {
  constructor(private kc: KeycloakService, private router: Router) {
    this.redirect();
  }

  private async redirect() {
    const logged = await this.kc.isLoggedIn();
    if (!logged) {
      await this.kc.login({ redirectUri: window.location.href });
      return;
    }

    const roles = this.kc.getUserRoles();
    const isAdmin = roles.includes('ADMIN') || roles.includes('ROLE_ADMIN');
    const isCoord =
      roles.includes('COORDENADOR') || roles.includes('ROLE_COORDENADOR');

    if (isAdmin) {
      this.router.navigateByUrl('/admin');
      return;
    }
    if (isCoord) {
      this.router.navigateByUrl('/coord');
      return;
    }

    this.router.navigateByUrl('/acesso-negado');
  }
}
