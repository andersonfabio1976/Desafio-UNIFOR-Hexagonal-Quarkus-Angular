import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home.component';

const routes: Routes = [
  {
    path: '',
    component: AdminHomeComponent,
    children: [
      { path: 'alunos', loadChildren: () => import('./pages/alunos/alunos.module').then(m => m.AlunosModule) },
      { path: 'professores', loadChildren: () => import('./pages/professores/professores.module').then(m => m.ProfessoresModule) },
      { path: 'cursos', loadChildren: () => import('./pages/cursos/cursos.module').then(m => m.CursosModule) },
      { path: 'matriculas', loadChildren: () => import('./pages/matriculas/matriculas.module').then(m => m.MatriculasModule) },
      { path: 'semestres', loadChildren: () => import('./pages/semestres/semestres.module').then(m => m.SemestresModule) },
      { path: '', redirectTo: 'alunos', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
