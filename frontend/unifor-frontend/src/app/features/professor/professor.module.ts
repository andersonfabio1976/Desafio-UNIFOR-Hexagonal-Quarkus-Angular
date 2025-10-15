import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

import { ProfessorRoutingModule } from './professor-routing.module';
import { ProfessorLayoutComponent } from './layout/professor-layout.component';
import { ProfessorMatrizComponent } from './pages/matriz/professor-matriz.component';

@NgModule({
  declarations: [
    ProfessorLayoutComponent,
    ProfessorMatrizComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    ProfessorRoutingModule,
  ],
})
export class ProfessorModule {}
