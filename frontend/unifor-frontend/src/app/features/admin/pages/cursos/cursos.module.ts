import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CursosComponent } from './cursos.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [CursosComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: '', component: CursosComponent }
    ])
  ]
})
export class CursosModule {}
