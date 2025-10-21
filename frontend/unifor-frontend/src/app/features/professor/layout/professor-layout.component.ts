import { Component } from '@angular/core';
import { AuthService } from '../../../core/auth/auth.service';

@Component({
  selector: 'app-professor-layout',
  templateUrl: './professor-layout.component.html',
  styleUrls: ['./professor-layout.component.scss'],
})
export class ProfessorLayoutComponent {
  constructor(private auth: AuthService) {}
  logout() { this.auth.logout(); }
}
