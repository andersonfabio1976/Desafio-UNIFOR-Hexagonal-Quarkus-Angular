import { Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent {
  constructor(private keycloak: KeycloakService) {}

  hasRole(role: string): boolean {
    const roles = this.keycloak.getUserRoles();
    return roles.includes(role);
  }

  logout() {
    this.keycloak.logout();
  }
}
