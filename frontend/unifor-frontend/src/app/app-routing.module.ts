import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { AdminGuard } from './core/auth/admin.guard';
import { CoordenadorGuard } from './core/auth/coordenador.guard';
// Substituímos temporariamente ProfessorGuard/AlunoGuard pelo guard de autenticado
import { AuthenticatedGuard } from './core/auth/authenticated.guard';

const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./core/auth/role-landing.component').then((m) => m.RoleLandingComponent),
    pathMatch: 'full',
  },
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'admin',
        canActivate: [AdminGuard],
        data: { roles: ['ADMIN', 'ROLE_ADMIN'] },
        loadChildren: () =>
          import('./features/admin/admin.module').then((m) => m.AdminModule),
      },
      {
        path: 'coord',
        canActivate: [CoordenadorGuard],
        data: { roles: ['COORDENADOR', 'ROLE_COORDENADOR'] },
        loadChildren: () =>
          import('./features/coordenador/coordenador.module').then((m) => m.CoordenadorModule),
      },
      // PROVISÓRIO: permitir professor autenticado (sem exigir role) para destravar
      {
        path: 'prof/matriz',
        canActivate: [AuthenticatedGuard],
        loadChildren: () =>
          import('./features/coordenador/pages/matriz/matriz.module').then((m) => m.MatrizModule),
      },
      // PROVISÓRIO: permitir aluno autenticado (sem exigir role) para destravar
      {
        path: 'aluno/matriz',
        canActivate: [AuthenticatedGuard],
        loadChildren: () =>
          import('./features/coordenador/pages/matriz/matriz.module').then((m) => m.MatrizModule),
      },
    ],
  },
  {
    path: 'acesso-negado',
    loadComponent: () =>
      import('./features/access-denied/access-denied.component').then((m) => m.AccessDeniedComponent),
  },
  { path: '**', redirectTo: 'admin' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
