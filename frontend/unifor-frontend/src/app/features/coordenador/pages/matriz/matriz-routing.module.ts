import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatrizComponent } from './matriz.component';

const routes: Routes = [
  { path: '', component: MatrizComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MatrizRoutingModule {}
