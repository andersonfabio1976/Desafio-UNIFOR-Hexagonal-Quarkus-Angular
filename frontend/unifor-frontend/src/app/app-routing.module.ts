import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { AdminGuard } from './core/auth/admin.guard';
import { CoordenadorGuard } from './core/auth/coordenador.guard';
import { AlunoGuard } from './core/auth/aluno.guard';
import { ProfessorGuard } from './core/auth/professor.guard';

const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./core/auth/role-landing.component').then((m) => m.RoleLandingComponent),
    pathMatch: 'full',
  },
  // Admin/Coord mantidos como estavam
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
      // REMOVIDO: rotas provisórias prof/matriz e aluno/matriz
    ],
  },
  // Novas áreas dedicadas
  {
    path: 'prof',
    canActivate: [ProfessorGuard],
    loadChildren: () =>
      import('./features/professor/professor.module').then((m) => m.ProfessorModule),
  },
  {
    path: 'aluno',
    canActivate: [AlunoGuard],
    loadChildren: () =>
      import('./features/aluno/aluno.module').then((m) => m.AlunoModule),
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
