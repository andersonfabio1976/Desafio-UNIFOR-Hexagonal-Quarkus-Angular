// src/app/core/layout/layout.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  isAdmin = false;
  isProfessor = false;
  isAluno = false;

  constructor(private keycloak: KeycloakService, private router: Router) {}

  async ngOnInit() {
    try {
      const roles = this.keycloak.getUserRoles(true);
      this.isAdmin = roles.includes('ADMIN');
      this.isProfessor = roles.includes('PROFESSOR');
      this.isAluno = roles.includes('ALUNO');

      // 🚀 Redirecionamento dinâmico pós-login
      if (this.isAdmin) {
        await this.router.navigate(['/admin']);
      } else if (this.isProfessor) {
        await this.router.navigate(['/professor']);
      } else if (this.isAluno) {
        await this.router.navigate(['/aluno']);
      } else {
        console.warn('Usuário autenticado, mas sem role reconhecida.');
      }
    } catch (err) {
      console.error('Erro ao obter roles do usuário:', err);
    }
  }

  logout() {
    this.keycloak.logout(window.location.origin);
  }
}
