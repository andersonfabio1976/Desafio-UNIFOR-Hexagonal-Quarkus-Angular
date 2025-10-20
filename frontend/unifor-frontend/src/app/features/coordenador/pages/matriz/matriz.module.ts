import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { MaterialModule } from '../../../../shared/material.module';
import { MatrizComponent } from './matriz.component';

@NgModule({
  declarations: [MatrizComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    RouterModule.forChild([{ path: '', component: MatrizComponent }]),
  ],
})
export class MatrizModule {}
