import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorLayoutComponent } from './layout/professor-layout.component';
import { ProfessorMatrizComponent } from './pages/matriz/professor-matriz.component';

const routes: Routes = [
  {
    path: '',
    component: ProfessorLayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'matriz' },
      { path: 'matriz', component: ProfessorMatrizComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProfessorRoutingModule {}
