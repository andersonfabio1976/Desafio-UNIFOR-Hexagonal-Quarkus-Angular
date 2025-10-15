import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CoordenadorRoutingModule } from './coordenador-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule,
    CoordenadorRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatTableModule,
  ],
})
export class CoordenadorModule {}
