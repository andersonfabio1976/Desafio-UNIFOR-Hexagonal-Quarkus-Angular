import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-matriz',
  template: `
    <mat-card>
      <h2>Matriz Curricular</h2>
      <p>Tela placeholder — CRUD completo será adicionado aqui.</p>
    </mat-card>
  `,
})
export class MatrizComponent {}

@NgModule({
  declarations: [MatrizComponent],
  imports: [
    CommonModule,
    MatCardModule,
    RouterModule.forChild([{ path: '', component: MatrizComponent }]),
  ],
})
export class MatrizModule {}
