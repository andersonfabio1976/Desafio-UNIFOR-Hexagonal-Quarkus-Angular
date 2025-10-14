import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccessDeniedComponent } from './access-denied.component';

const routes: Routes = [
  { path: '', component: AccessDeniedComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    // Se seu component for standalone, não precisa declarar em declarations
  ]
})
export class AccessDeniedModule {}
