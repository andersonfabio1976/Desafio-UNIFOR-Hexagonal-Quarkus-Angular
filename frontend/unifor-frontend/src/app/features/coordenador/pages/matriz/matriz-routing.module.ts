import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatrizComponent } from './matriz.component';

const routes: Routes = [
  // rota padrão do módulo lazy
  { path: '', component: MatrizComponent },
  // se tiver subpáginas, defina aqui como children
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MatrizRoutingModule {}
