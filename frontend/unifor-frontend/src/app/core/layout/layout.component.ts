import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LayoutComponent implements OnInit {
  constructor(public auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    // Loga payload e roles no console do navegador para ajuste fino dos guards
    try { this.auth.debugPrintClaims(); } catch {}
  }

  isAdmin(): boolean {
    return this.auth.hasAnyRole(['ADMIN', 'ROLE_ADMIN', 'ADMINISTRADOR', 'ROLE_ADMINISTRADOR']);
  }
  isCoord(): boolean {
    return this.auth.hasAnyRole(['COORDENADOR', 'ROLE_COORDENADOR', 'COORDINATOR']);
  }
  isProfessorNaoCoord(): boolean {
    return this.auth.hasAnyRole(['PROFESSOR', 'ROLE_PROFESSOR', 'DOCENTE', 'TEACHER']) && !this.isCoord();
  }
  isAluno(): boolean {
    return this.auth.hasAnyRole(['ALUNO', 'ROLE_ALUNO', 'ESTUDANTE', 'STUDENT']);
  }

  private urlStarts(path: string): boolean { try { return this.router.url?.startsWith(path); } catch { return false; } }
  showAdminSection(): boolean { return this.isAdmin() || this.urlStarts('/admin'); }
  showCoordSection(): boolean { return this.isCoord()  || this.urlStarts('/coord'); }
  showProfessorSection(): boolean { return this.isProfessorNaoCoord() || this.urlStarts('/prof');}
  showAlunoSection(): boolean { return this.isAluno() || this.urlStarts('/aluno'); }
}
