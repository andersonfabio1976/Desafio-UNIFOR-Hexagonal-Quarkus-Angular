import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { SemestreDTO } from './semestre.dto';

@Injectable({ providedIn: 'root' })
export class SemestreService {
  private baseUrl = `${environment.apiUrl}/Semestres`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<SemestreDTO[]> {
    return this.http.get<SemestreDTO[]>(this.baseUrl);
  }

  buscarPorId(id: number): Observable<SemestreDTO> {
    return this.http.get<SemestreDTO>(`${this.baseUrl}/${id}`);
  }

  salvar(dto: SemestreDTO): Observable<SemestreDTO> {
    if (dto.identifier) {
      return this.http.put<SemestreDTO>(`${this.baseUrl}/${dto.identifier}`, dto);
    }
    return this.http.post<SemestreDTO>(this.baseUrl, dto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
