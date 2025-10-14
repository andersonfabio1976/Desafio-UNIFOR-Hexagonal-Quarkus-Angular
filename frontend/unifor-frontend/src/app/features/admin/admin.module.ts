import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

import { AdminHomeComponent } from './admin-home.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminWelcomeComponent } from './admin-welcome.component';

@NgModule({
  declarations: [
    AdminHomeComponent,
    AdminWelcomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,        // habilita <router-outlet> e diretivas de roteamento
    MatSidenavModule,    // habilita mat-sidenav(-container/-content)
    MatListModule,       // habilita mat-nav-list
    MatButtonModule,
    MatCardModule,
    AdminRoutingModule   // registra as rotas filhas do admin
  ]
})
export class AdminModule {}
