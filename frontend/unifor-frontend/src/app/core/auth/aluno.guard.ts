import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';
import { KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class AlunoGuard implements CanActivate {
  constructor(
    private auth: AuthService,
    private router: Router,
    private keycloak: KeycloakService
  ) {}

  private norm(v: string): string {
    if (!v) return '';
    let x = v.toUpperCase().trim();
    x = x.replace(/^ROLE_/, '').replace(/^GROUP_/, '').replace(/^SCOPE_/, '');
    return x.replace(/[^A-Z0-9_]/g, '');
  }

  canActivate(): boolean | UrlTree {
    const targets = ['ALUNO', 'ROLE_ALUNO', 'ESTUDANTE', 'STUDENT'];
    const okAuth = this.auth.hasAnyRole(targets);
    const kcRolesRaw = this.keycloak.getUserRoles(true);
    const kc = new Set(kcRolesRaw.map(r => this.norm(r)));
    const okKC = targets.some(t => kc.has(this.norm(t)));

    // eslint-disable-next-line no-console
    console.log('[AlunoGuard] okAuth:', okAuth, 'kcRoles:', kcRolesRaw, 'okKC:', okKC);

    const ok = okAuth || okKC;
    return ok ? true : this.router.parseUrl('/acesso-negado');
  }
}
