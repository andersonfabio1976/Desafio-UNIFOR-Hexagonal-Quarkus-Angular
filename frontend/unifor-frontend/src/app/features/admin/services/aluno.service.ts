import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface Aluno {
  identifier?: number;
  nome: string;
  email: string;
  dataNascimento: string;
}

@Injectable({ providedIn: 'root' })
export class AlunoService {
  private apiUrl = `${environment.apiUrl}/alunos`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.apiUrl);
  }

  salvar(aluno: Aluno): Observable<void> {
    return this.http.post<void>(this.apiUrl, aluno);
  }

  excluir(identifier: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${identifier}`);
  }
}
