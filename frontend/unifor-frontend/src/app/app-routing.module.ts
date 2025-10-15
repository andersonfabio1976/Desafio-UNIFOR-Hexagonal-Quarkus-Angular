import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { AdminGuard } from './core/auth/admin.guard';
import { CoordenadorGuard } from './core/auth/coordenador.guard';

const routes: Routes = [
  { path: '', redirectTo: 'admin', pathMatch: 'full' }, // 👈 redirecionamento inicial

  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'admin',
        canActivate: [AdminGuard],
        loadChildren: () =>
          import('./features/admin/admin.module').then((m) => m.AdminModule),
      },
      {
        path: 'coord',
        canActivate: [CoordenadorGuard],
        loadChildren: () =>
          import('./features/coordenador/coordenador.module').then(
            (m) => m.CoordenadorModule
          ),
      },
    ],
  },
  { path: '**', redirectTo: 'admin' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
