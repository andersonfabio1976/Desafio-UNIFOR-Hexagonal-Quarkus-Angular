import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../../../shared/material.module';
import { DisciplinaComponent } from './disciplina.component';

@NgModule({
  declarations: [DisciplinaComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild([{ path: '', component: DisciplinaComponent }]),
    MaterialModule
  ]
})
export class DisciplinaModule {}
