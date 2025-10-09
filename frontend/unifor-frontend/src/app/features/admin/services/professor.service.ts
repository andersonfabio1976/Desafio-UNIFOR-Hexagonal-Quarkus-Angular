import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface Professor {
  identifier?: number;
  nome: string;
  email: string;
  area: string;
  coordenador: boolean;
}

@Injectable({ providedIn: 'root' })
export class ProfessorService {
  private apiUrl = `${environment.apiUrl}/professores`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Professor[]> {
    return this.http.get<Professor[]>(this.apiUrl);
  }

  salvar(professor: Professor): Observable<void> {
    return this.http.post<void>(this.apiUrl, professor);
  }

  remover(identifier: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${identifier}`);
  }
}
