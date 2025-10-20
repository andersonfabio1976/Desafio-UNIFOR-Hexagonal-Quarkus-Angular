import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../../../shared/material.module';
import { CursoComponent } from './curso.component';

@NgModule({
  declarations: [CursoComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    RouterModule.forChild([{ path: '', component: CursoComponent }]),
    MaterialModule
  ]
})
export class CursoModule {}
