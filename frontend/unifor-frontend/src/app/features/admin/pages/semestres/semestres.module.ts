import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SemestresComponent } from './semestres.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [SemestresComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: '', component: SemestresComponent }
    ])
  ]
})
export class SemestresModule {}
