import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';

export interface ProfessorDTO {
  identifier?: number;
  nome: string;
  email: string;
  area: string;
  disciplinas?: any[];
  coordenador: boolean;
  createdOn?: string;
  updatedOn?: string;
}

@Injectable({ providedIn: 'root' })
export class ProfessoresService {
  private base = `${environment.apiUrl}/professores`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<ProfessorDTO[]> {
    return this.http.get<ProfessorDTO[]>(this.base);
  }

  buscarPorId(id: number): Observable<ProfessorDTO> {
    return this.http.get<ProfessorDTO>(`${this.base}/${id}`);
  }

  salvar(dto: ProfessorDTO): Observable<ProfessorDTO> {
    if (dto.identifier) {
      return this.http.put<ProfessorDTO>(`${this.base}/${dto.identifier}`, dto);
    }
    return this.http.post<ProfessorDTO>(this.base, dto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${id}`);
  }
}
