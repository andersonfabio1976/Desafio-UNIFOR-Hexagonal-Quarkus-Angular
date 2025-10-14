import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { AdminGuard } from './core/auth/admin.guard';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'admin', pathMatch: 'full' },
      {
        path: 'admin',
        canActivate: [AdminGuard],
        canActivateChild: [AdminGuard],
        data: { roles: ['ADMIN'] },
        loadChildren: () =>
          import('./features/admin/admin.module').then((m) => m.AdminModule),
      },
      // Remova as rotas de professor e aluno se não tiver módulos e guards próprios
      // {
      //   path: 'professor',
      //   loadChildren: () =>
      //     import('./features/admin/pages/professores/professores.module').then((m) => m.ProfessoresModule),
      // },
      // {
      //   path: 'aluno',
      //   loadChildren: () =>
      //     import('./features/admin/pages/alunos/alunos.module').then((m) => m.AlunosModule),
      // },
    ],
  },
  {
    path: 'acesso-negado',
    loadChildren: () =>
      import('./features/access-denied/access-denied.module').then((m) => m.AccessDeniedModule),
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
