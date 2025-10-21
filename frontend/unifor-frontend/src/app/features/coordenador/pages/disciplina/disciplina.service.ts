import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { DisciplinaDTO } from './disciplina.dto';

@Injectable({ providedIn: 'root' })
export class DisciplinaService {
  private baseUrl = `${environment.apiUrl}/Disciplinas`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<DisciplinaDTO[]> {
    return this.http.get<DisciplinaDTO[]>(this.baseUrl);
  }

  buscarPorId(id: number): Observable<DisciplinaDTO> {
    return this.http.get<DisciplinaDTO>(`${this.baseUrl}/${id}`);
  }

  salvar(dto: DisciplinaDTO): Observable<DisciplinaDTO> {
    if (dto.identifier) {
      return this.http.put<DisciplinaDTO>(`${this.baseUrl}/${dto.identifier}`, dto);
    }
    return this.http.post<DisciplinaDTO>(this.baseUrl, dto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
