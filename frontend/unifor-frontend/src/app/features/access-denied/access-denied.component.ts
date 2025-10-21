import { Component } from '@angular/core';

@Component({
  selector: 'app-access-denied',
  standalone: true,
  template: `<h2>Acesso negado</h2><p>Você não tem permissão para acessar esta área.</p>`
})
export class AccessDeniedComponent {}
