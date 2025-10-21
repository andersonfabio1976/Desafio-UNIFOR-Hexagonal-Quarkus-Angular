import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'unifor-frontend';
  username = 'Administrador';

  hasRole(role: string): boolean {
    const mockRoles = ['ADMIN', 'COORDENADOR', 'PROFESSOR', 'ALUNO'];
    return mockRoles.includes(role);
  }

  ngOnInit() {
    console.log('✅ Angular inicializou completamente.');
  }

}
