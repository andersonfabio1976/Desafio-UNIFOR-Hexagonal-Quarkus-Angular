import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'semestres', loadChildren: () => import('./pages/semestre/semestre.module').then(m => m.SemestreModule) },
  { path: 'cursos', loadChildren: () => import('./pages/curso/curso.module').then(m => m.CursoModule) },
  { path: 'disciplinas', loadChildren: () => import('./pages/disciplina/disciplina.module').then(m => m.DisciplinaModule) },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoordenadorRoutingModule {}
