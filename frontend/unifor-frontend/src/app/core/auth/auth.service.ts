import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

type Jwt = {
  exp?: number;
  realm_access?: { roles?: string[] };
  resource_access?: Record<string, { roles?: string[] }>;
  groups?: string[];
  authorities?: string[];
  scope?: string;
  roles?: string[];
};

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly tokenKeys = ['access_token', 'kc_token', 'token'];

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;
    const payload = this.parseJwt(token);
    if (!payload?.exp) return true;
    return payload.exp * 1000 > Date.now();
  }

  hasAnyRole(targets: string[]): boolean {
    const all = this.getAllRoles();
    const wanted = targets.map((r) => this.norm(r));
    return all.some((r) => wanted.includes(r));
  }

  // Útil para ver claims/roles no console
  debugPrintClaims(): void {
    const token = this.getToken();
    const payload = this.parseJwt(token);
    // eslint-disable-next-line no-console
    console.log('[AuthService] payload:', payload);
    // eslint-disable-next-line no-console
    console.log('[AuthService] roles extraídas:', this.getAllRoles());
  }

  logout(): void {
    this.tokenKeys.forEach((k) => {
      localStorage.removeItem(k);
      sessionStorage.removeItem(k);
    });
    const kc = (environment as any).keycloak;
    if (kc?.url && kc?.realm && kc?.clientId) {
      const redirect = encodeURIComponent(window.location.origin);
      const url = `${kc.url}/realms/${kc.realm}/protocol/openid-connect/logout?client_id=${kc.clientId}&post_logout_redirect_uri=${redirect}`;
      window.location.href = url;
      return;
    }
    window.location.href = '/';
  }

  private getToken(): string | null {
    for (const k of this.tokenKeys) {
      const v = localStorage.getItem(k) || sessionStorage.getItem(k);
      if (v) return v;
    }
    return null;
  }

  private parseJwt(token: string | null): Jwt | null {
    if (!token) return null;
    try {
      const base64 = token.split('.')[1];
      const json = decodeURIComponent(
        atob(base64)
          .split('')
          .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
          .join('')
      );
      return JSON.parse(json);
    } catch {
      return null;
    }
  }

  private getAllRoles(): string[] {
    const token = this.getToken();
    const payload = this.parseJwt(token);
    if (!payload) return [];

    const result: string[] = [];
    const pushRoles = (arr?: string[]) => {
      if (!arr) return;
      for (const r of arr) {
        const n = r ? this.norm(r) : '';
        if (n) result.push(n);
      }
    };

    // 1) Realm roles
    pushRoles(payload.realm_access?.roles);

    // 2) Client roles (seguro, agrega todos os clients também)
    const ra = payload.resource_access ?? {};
    const clientId = (environment as any)?.keycloak?.clientId as string | undefined;
    if (clientId && Object.prototype.hasOwnProperty.call(ra, clientId)) {
      const client = (ra as Record<string, { roles?: string[] }>)[clientId];
      pushRoles(client?.roles);
    }
    // agrega os demais clients como fallback/extra
    for (const v of Object.values(ra)) {
      pushRoles(v?.roles);
    }

    // 3) Groups → quebramos caminho e empurramos cada segmento
    if (Array.isArray(payload.groups)) {
      for (const g of payload.groups) {
        const cleaned = (g || '').replace(/^\/+/, ''); // remove barras iniciais
        if (!cleaned) continue;

        // também conta o caminho completo
        const full = this.norm(cleaned);
        if (full) result.push(full);

        // e cada segmento separadamente (/roles/professor → ROLES, PROFESSOR)
        const parts = cleaned.split('/').filter(Boolean);
        for (const p of parts) {
          const n = this.norm(p);
          if (n) result.push(n);
        }
      }
    }

    // 4) Authorities
    pushRoles(payload.authorities);

    // 5) Scope (ex.: "openid profile email aluno professor")
    if (payload.scope) {
      for (const s of payload.scope.split(' ')) {
        const n = this.norm(s);
        if (n) result.push(n);
      }
    }

    // 6) roles (fallback)
    pushRoles(payload.roles);

    // remove duplicados
    return Array.from(new Set(result));
  }

  private norm(v: string): string {
    if (!v) return '';
    let x = v.toUpperCase().trim();
    x = x.replace(/^ROLE_/, '')
      .replace(/^GROUP_/, '')
      .replace(/^SCOPE_/, '');

    // aliases comuns
    if (x === 'ADMINISTRADOR') x = 'ADMIN';
    if (x === 'COORDINATOR') x = 'COORDENADOR';
    if (x === 'DOCENTE' || x === 'TEACHER') x = 'PROFESSOR';
    if (x === 'ESTUDANTE' || x === 'STUDENT') x = 'ALUNO';

    // alguns grupos vêm com hífens ou nomes mais longos (ex.: ROLE-PROFESSOR)
    x = x.replace(/[^A-Z0-9_]/g, ''); // remove caract. não alfanum./underscore

    return x;
  }
}
