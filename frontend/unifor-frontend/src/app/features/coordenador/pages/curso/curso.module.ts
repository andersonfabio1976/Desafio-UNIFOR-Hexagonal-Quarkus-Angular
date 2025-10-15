import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-curso',
  template: `
    <mat-card>
      <h2>Gestão de Cursos</h2>
      <p>Tela placeholder — CRUD completo será adicionado aqui.</p>
    </mat-card>
  `,
})
export class CursoComponent {}

@NgModule({
  declarations: [CursoComponent],
  imports: [
    CommonModule,
    MatCardModule,
    RouterModule.forChild([{ path: '', component: CursoComponent }]),
  ],
})
export class CursoModule {} // 👈 Nome idêntico ao do then()
