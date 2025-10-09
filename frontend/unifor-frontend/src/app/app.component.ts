import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  username = 'Administrador';

  hasRole(role: string): boolean {
    const mockRoles = ['ADMIN', 'COORDENADOR', 'PROFESSOR', 'ALUNO'];
    return mockRoles.includes(role);
  }
}
