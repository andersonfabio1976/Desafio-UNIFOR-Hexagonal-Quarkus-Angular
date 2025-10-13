import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SemestresRoutingModule } from './semestres-routing.module';
import { SemestresComponent } from './semestres.component';
import { MaterialModule } from 'src/app/shared/material.module';

@NgModule({
  declarations: [SemestresComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SemestresRoutingModule
  ]
})
export class SemestresModule {}
