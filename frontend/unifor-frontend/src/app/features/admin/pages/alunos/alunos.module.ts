import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AlunosComponent } from './alunos.component';
import { MaterialModule } from '../../../../shared/material.module';

const routes: Routes = [{ path: '', component: AlunosComponent }];

@NgModule({
  declarations: [AlunosComponent],
  imports: [CommonModule, ReactiveFormsModule, MaterialModule, RouterModule.forChild(routes)],
})
export class AlunosModule {}
