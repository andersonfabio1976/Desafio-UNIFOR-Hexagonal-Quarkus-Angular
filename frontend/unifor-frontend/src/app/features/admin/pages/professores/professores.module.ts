import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfessoresComponent } from './professores.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [ProfessoresComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: '', component: ProfessoresComponent }
    ])
  ]
})
export class ProfessoresModule {}
