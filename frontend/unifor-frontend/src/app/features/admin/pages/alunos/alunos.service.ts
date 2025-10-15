import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { AlunoDTO } from './aluno.dto';

@Injectable({
  providedIn: 'root'
})
export class AlunosService {
  private baseUrl = `${environment.apiUrl}/alunos`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<AlunoDTO[]> {
    return this.http.get<AlunoDTO[]>(this.baseUrl);
  }

  buscarPorId(id: number): Observable<AlunoDTO> {
    return this.http.get<AlunoDTO>(`${this.baseUrl}/${id}`);
  }

  salvar(dto: AlunoDTO): Observable<AlunoDTO> {
    if (dto.identifier) {
      // ✅ corrigido aqui
      return this.http.put<AlunoDTO>(`${this.baseUrl}/${dto.identifier}`, dto);
    }
    return this.http.post<AlunoDTO>(this.baseUrl, dto);
  }

  remover(id: number): Observable<void> {
    // ✅ corrigido aqui também
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
