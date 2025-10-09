import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminHomeComponent } from './admin-home.component';

// Páginas
import { AlunosComponent } from './pages/alunos/alunos.component';
import { CursosComponent } from './pages/cursos/cursos.component';
import { ProfessoresComponent } from './pages/professores/professores.component';
import { MatriculasComponent } from './pages/matriculas/matriculas.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';

// Angular Material imports
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

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

    // Angular Material
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatTableModule,
    MatSnackBarModule,
    MatListModule,
    MatSidenavModule,
    MatDividerModule,
    MatProgressSpinnerModule
  ],
  providers: [DatePipe]
})
export class AdminModule {}
