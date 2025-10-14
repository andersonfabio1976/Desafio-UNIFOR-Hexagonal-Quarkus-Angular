import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatriculasComponent } from './matriculas.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [MatriculasComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: '', component: MatriculasComponent }
    ])
  ]
})
export class MatriculasModule {}
