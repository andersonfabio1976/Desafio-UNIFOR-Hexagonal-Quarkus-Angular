import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // <-- Aqui
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
// outros imports...

@NgModule({
  declarations: [
    AppComponent,
    // outros componentes...
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, // <-- Aqui
    AppRoutingModule,
    // outros módulos...
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
