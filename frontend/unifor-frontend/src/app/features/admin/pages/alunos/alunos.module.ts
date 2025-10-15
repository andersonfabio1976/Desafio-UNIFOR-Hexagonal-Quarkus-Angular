import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AlunosComponent } from './alunos.component';
import { MaterialModule } from '../../../../shared/material.module';

@NgModule({
  declarations: [AlunosComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    MaterialModule, // 👈 todos os componentes Material estão aqui
  ],
  exports: [AlunosComponent],
})
export class AlunosModule {}
