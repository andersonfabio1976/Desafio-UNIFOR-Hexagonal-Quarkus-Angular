import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatrizComponent } from './matriz.component';
import { MatrizRoutingModule } from './matriz-routing.module';

@NgModule({
  declarations: [MatrizComponent],
  imports: [
    CommonModule,
    MatrizRoutingModule, // importante: registra as rotas internas
  ],
})
export class MatrizModule {}
