import { Component } from '@angular/core';
import { AuthService } from '../../../core/auth/auth.service';

@Component({
  selector: 'app-aluno-layout',
  templateUrl: './aluno-layout.component.html',
  styleUrls: ['./aluno-layout.component.scss'],
})
export class AlunoLayoutComponent {
  constructor(private auth: AuthService) {}
  logout() { this.auth.logout(); }
}
