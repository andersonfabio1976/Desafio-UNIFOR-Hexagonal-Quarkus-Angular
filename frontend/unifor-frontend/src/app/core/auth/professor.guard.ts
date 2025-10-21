import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';
import { KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class ProfessorGuard implements CanActivate {
  constructor(
    private auth: AuthService,
    private router: Router,
    private keycloak: KeycloakService
  ) {}

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

  canActivate(): boolean | UrlTree {
    const targets = ['PROFESSOR', 'ROLE_PROFESSOR', 'DOCENTE', 'TEACHER'];
    const okAuth = this.auth.hasAnyRole(targets);
    const kcRolesRaw = this.keycloak.getUserRoles(true); // inclui client roles
    const kc = new Set(kcRolesRaw.map(r => this.norm(r)));
    const okKC = targets.some(t => kc.has(this.norm(t)));
    console.log('[ProfessorGuard] okAuth:', okAuth, 'kcRoles:', kcRolesRaw, 'okKC:', okKC);
    const ok = okAuth || okKC;
    return ok ? true : this.router.parseUrl('/acesso-negado');
  }
}
