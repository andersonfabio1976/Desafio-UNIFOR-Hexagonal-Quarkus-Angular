import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';

export interface MatrizItemDTO {
  periodo: number;
  disciplina: string;
  cargaHoraria: number | null;
  professor: string;
}

@Injectable({ providedIn: 'root' })
export class MatrizService {
  private baseUrl = `${environment.apiUrl}/Matriz`;

  constructor(private http: HttpClient) {}

  listarPorCurso(cursoId: number): Observable<MatrizItemDTO[]> {
    return this.http.get<MatrizItemDTO[]>(`${this.baseUrl}?cursoId=${cursoId}`);
  }
}
