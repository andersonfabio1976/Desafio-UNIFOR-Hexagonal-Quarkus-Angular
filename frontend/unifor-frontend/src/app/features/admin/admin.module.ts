import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdminRoutingModule } from './admin-routing.module';
import { AlunosModule } from './pages/alunos/alunos.module';
import { MaterialModule } from '../../shared/material.module';

import { AdminHomeComponent } from './admin-home.component';
import { AdminWelcomeComponent } from './admin-welcome.component';

@NgModule({
  declarations: [
    AdminHomeComponent,
    AdminWelcomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    AdminRoutingModule,
    AlunosModule,
    MaterialModule, // 👈 adiciona os componentes do Angular Material
  ],
})
export class AdminModule {}
