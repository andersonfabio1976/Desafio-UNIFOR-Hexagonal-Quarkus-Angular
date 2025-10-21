import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { CursoDTO } from './curso.dto';

@Injectable({ providedIn: 'root' })
export class CursoService {
  private baseUrl = `${environment.apiUrl}/Cursos`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<CursoDTO[]> {
    return this.http.get<CursoDTO[]>(this.baseUrl);
  }

  buscarPorId(id: number): Observable<CursoDTO> {
    return this.http.get<CursoDTO>(`${this.baseUrl}/${id}`);
  }

  salvar(dto: CursoDTO): Observable<CursoDTO> {
    if (dto.identifier) {
      return this.http.put<CursoDTO>(`${this.baseUrl}/${dto.identifier}`, dto);
    }
    return this.http.post<CursoDTO>(this.baseUrl, dto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
