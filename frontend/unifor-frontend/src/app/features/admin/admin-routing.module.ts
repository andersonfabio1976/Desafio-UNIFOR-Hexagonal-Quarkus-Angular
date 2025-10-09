import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home.component';
import { AlunosComponent } from './pages/alunos/alunos.component';
import { CursosComponent } from './pages/cursos/cursos.component';
import { ProfessoresComponent } from './pages/professores/professores.component';
import { MatriculasComponent } from './pages/matriculas/matriculas.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';

const routes: Routes = [
  {
    path: '',
    component: AdminHomeComponent,
    children: [
      { path: 'alunos', component: AlunosComponent },
      { path: 'cursos', component: CursosComponent },
      { path: 'professores', component: ProfessoresComponent },
      { path: 'matriculas', component: MatriculasComponent },
      { path: 'usuarios', component: UsuariosComponent },
      { path: '', redirectTo: 'alunos', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
