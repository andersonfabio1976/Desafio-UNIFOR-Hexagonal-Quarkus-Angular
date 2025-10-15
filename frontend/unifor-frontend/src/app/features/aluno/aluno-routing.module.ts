import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlunoLayoutComponent } from './layout/aluno-layout.component';
import { AlunoMatrizComponent } from './pages/matriz/aluno-matriz.component';

const routes: Routes = [
  {
    path: '',
    component: AlunoLayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'matriz' },
      { path: 'matriz', component: AlunoMatrizComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AlunoRoutingModule {}
