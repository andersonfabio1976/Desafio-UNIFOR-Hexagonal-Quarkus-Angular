import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-disciplina',
  template: `
    <mat-card>
      <h2>Gestão de Disciplinas</h2>
      <p>Tela placeholder — CRUD completo será adicionado aqui.</p>
    </mat-card>
  `,
})
export class DisciplinaComponent {}

@NgModule({
  declarations: [DisciplinaComponent],
  imports: [
    CommonModule,
    MatCardModule,
    RouterModule.forChild([{ path: '', component: DisciplinaComponent }]),
  ],
})
export class DisciplinaModule {}
