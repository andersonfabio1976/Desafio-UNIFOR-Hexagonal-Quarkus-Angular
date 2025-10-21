import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { MaterialModule } from 'src/app/shared/material.module';

// Páginas
import { AdminHomeComponent } from './admin-home.component';
import { AlunosComponent } from './pages/alunos/alunos.component';
import { CursosComponent } from './pages/cursos/cursos.component';
import { ProfessoresComponent } from './pages/professores/professores.component';
import { MatriculasComponent } from './pages/matriculas/matriculas.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';

@NgModule({
  declarations: [
    AdminHomeComponent,
    AlunosComponent,
    CursosComponent,
    ProfessoresComponent,
    MatriculasComponent,
    UsuariosComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule,
    MaterialModule
  ],
  providers: [DatePipe]
})
export class AdminModule {}
