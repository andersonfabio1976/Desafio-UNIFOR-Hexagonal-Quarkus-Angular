import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AlunoDTO } from './aluno.dto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AlunosService {
  private readonly API = 'http://localhost:8081/alunos';

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<AlunoDTO[]> {
    return this.http.get<AlunoDTO[]>(this.API);
  }

  buscarPorId(identifier: number): Observable<AlunoDTO> {
    return this.http.get<AlunoDTO>(`${this.API}/${identifier}`);
  }

  salvar(aluno: AlunoDTO): Observable<void> {
    return this.http.post<void>(this.API, aluno);
  }

  remover(identifier: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.API}/${identifier}`);
  }
}
