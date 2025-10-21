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

import { AlunoRoutingModule } from './aluno-routing.module';
import { AlunoLayoutComponent } from './layout/aluno-layout.component';
import { AlunoMatrizComponent } from './pages/matriz/aluno-matriz.component';

@NgModule({
  declarations: [
    AlunoLayoutComponent,
    AlunoMatrizComponent,
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
    AlunoRoutingModule,
  ],
})
export class AlunoModule {}
