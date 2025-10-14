import { AfterViewInit, Component, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss'],
})
export class AdminHomeComponent implements AfterViewInit {
  @ViewChild('sidenav') sidenav!: MatSidenav;
  isHandset = false;

  constructor(
    private bo: BreakpointObserver,
    private router: Router,
    private keycloak: KeycloakService,
    private cdr: ChangeDetectorRef
  ) {}

  ngAfterViewInit(): void {
    // Observa Handset e Tablet – ajuste se quiser só Handset
    this.bo.observe([Breakpoints.Handset, Breakpoints.Tablet]).subscribe(result => {
      this.isHandset = result.matches;

      if (this.sidenav) {
        this.sidenav.mode = this.isHandset ? 'over' : 'side';
        if (this.isHandset) {
          this.sidenav.close();
        } else {
          this.sidenav.open();
        }
        // Evita ExpressionChangedAfterItHasBeenCheckedError
        this.cdr.detectChanges();
      }
    });

    this.router.events
      .pipe(filter(e => e instanceof NavigationEnd))
      .subscribe(() => {
        if (this.isHandset && this.sidenav?.opened) {
          this.sidenav.close();
        }
      });
  }

  async logout() {
    try {
      await this.keycloak.logout(window.location.origin);
    } catch (e) {
      console.error('Erro ao fazer logout', e);
    }
  }
}
