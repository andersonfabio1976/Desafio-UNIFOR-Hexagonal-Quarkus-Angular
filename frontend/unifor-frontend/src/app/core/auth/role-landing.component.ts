import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  standalone: true,
  selector: 'app-role-landing',
  template: `<p style="margin:16px">Redirecionando...</p>`,
})
export class RoleLandingComponent {
  constructor(private kc: KeycloakService, private router: Router) {
    this.redirect();
  }

  private norm(v: string): string {
    if (!v) return '';
    let x = v.toUpperCase().trim();
    x = x.replace(/^ROLE_/, '').replace(/^GROUP_/, '').replace(/^SCOPE_/, '');
    if (x === 'ADMINISTRADOR') x = 'ADMIN';
    if (x === 'COORDINATOR') x = 'COORDENADOR';
    if (x === 'DOCENTE' || x === 'TEACHER') x = 'PROFESSOR';
    if (x === 'ESTUDANTE' || x === 'STUDENT') x = 'ALUNO';
    return x.replace(/[^A-Z0-9_]/g, '');
  }

  private async redirect() {
    const logged = await this.kc.isLoggedIn();
    if (!logged) {
      await this.kc.login({ redirectUri: window.location.href });
      return;
    }

    const rolesRaw = this.kc.getUserRoles(true);
    const roles = new Set(rolesRaw.map(r => this.norm(r)));
    console.log('[RoleLanding] roles:', rolesRaw);
    if (roles.has('ADMIN')) {
      this.router.navigateByUrl('/admin');
      return;
    }
    if (roles.has('COORDENADOR')) {
      this.router.navigateByUrl('/coord');
      return;
    }
    if (roles.has('PROFESSOR')) {
      this.router.navigateByUrl('/prof');
      return;
    }
    if (roles.has('ALUNO')) {
      this.router.navigateByUrl('/aluno');
      return;
    }

    this.router.navigateByUrl('/acesso-negado');
  }
}
