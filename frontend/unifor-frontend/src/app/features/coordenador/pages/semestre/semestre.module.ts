import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-semestre',
  template: `
    <mat-card>
      <h2>Gestão de Semestres</h2>
      <p>Tela placeholder — CRUD completo será adicionado aqui.</p>
    </mat-card>
  `,
})
export class SemestreComponent {}

@NgModule({
  declarations: [SemestreComponent],
  imports: [
    CommonModule,
    MatCardModule,
    RouterModule.forChild([{ path: '', component: SemestreComponent }]),
  ],
})
export class SemestreModule {} // 👈 Nome idêntico ao do then()
