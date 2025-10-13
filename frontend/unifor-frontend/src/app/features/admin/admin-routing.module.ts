import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home.component';

const routes: Routes = [
  { path: '', component: AdminHomeComponent }, // Home do admin
  {
    path: 'alunos',
    loadChildren: () =>
      import('./pages/alunos/alunos.module').then((m) => m.AlunosModule),
  },
  // Outros módulos depois
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
