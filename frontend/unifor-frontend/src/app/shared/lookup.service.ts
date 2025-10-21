import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface IdName {
  identifier: number;
  nome: string;
}

@Injectable({ providedIn: 'root' })
export class LookupService {
  private alunosUrl = `${environment.apiUrl}/alunos`;
  private professoresUrl = `${environment.apiUrl}/professores`;
  private cursosUrl = `${environment.apiUrl}/Cursos`;
  private semestresUrl = `${environment.apiUrl}/Semestres`;

  constructor(private http: HttpClient) {}

  alunos(): Observable<IdName[]> {
    return this.http.get<IdName[]>(this.alunosUrl);
  }

  professores(): Observable<IdName[]> {
    return this.http.get<IdName[]>(this.professoresUrl);
  }

  cursos(): Observable<IdName[]> {
    return this.http.get<IdName[]>(this.cursosUrl);
  }

  semestres(): Observable<IdName[]> {
    return this.http.get<IdName[]>(this.semestresUrl);
  }
}
