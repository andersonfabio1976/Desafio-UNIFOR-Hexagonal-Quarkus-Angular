import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SemestresComponent } from './semestres.component';

const routes: Routes = [
  { path: '', component: SemestresComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SemestresRoutingModule {}
