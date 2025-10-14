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

    } catch (err) {
      console.error('Erro ao obter roles do usuário:', err);
    }
  }

  logout() {
    this.keycloak.logout(window.location.origin);
  }
}
