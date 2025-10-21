import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  isAdmin = false;
  isCoordenador = false;
  isProfessor = false;
  isAluno = false;

  constructor(private keycloak: KeycloakService) {}

  async ngOnInit() {
    const roles = this.keycloak.getUserRoles();
    this.isAdmin = roles.includes('admin');
    this.isCoordenador = roles.includes('coordenador');
    this.isProfessor = roles.includes('professor');
    this.isAluno = roles.includes('aluno');
  }

  async logout() {
    await this.keycloak.logout(window.location.origin);
  }
}
