import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { ProfessorDTO } from './professor.dto';

@Injectable({
  providedIn: 'root'
})
export class ProfessoresService {
  private readonly baseUrl = `${environment.apiUrl}/professores`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<ProfessorDTO[]> {
    return this.http.get<ProfessorDTO[]>(this.baseUrl);
  }

  buscarPorId(id: number): Observable<ProfessorDTO> {
    return this.http.get<ProfessorDTO>(`${this.baseUrl}/${id}`);
  }

  salvar(dto: ProfessorDTO): Observable<ProfessorDTO> {
    if (dto.identifier) {
      return this.http.put<ProfessorDTO>(`${this.baseUrl}/${dto.identifier}`, dto);
    }
    return this.http.post<ProfessorDTO>(this.baseUrl, dto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
