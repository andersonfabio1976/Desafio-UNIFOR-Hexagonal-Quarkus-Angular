import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AlunoGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(): boolean | UrlTree {
    // aceita múltiplas representações/sinônimos
    const ok = this.auth.hasAnyRole(['ALUNO', 'ROLE_ALUNO', 'ESTUDANTE', 'STUDENT']);
    return ok ? true : this.router.parseUrl('/acesso-negado');
  }
}
