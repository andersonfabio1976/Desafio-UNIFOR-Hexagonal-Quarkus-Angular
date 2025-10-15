import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { AlunoDTO } from './aluno.dto';

@Injectable({ providedIn: 'root' })
export class AlunosService {
  private baseUrl = `${environment.apiUrl}/alunos`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<AlunoDTO[]> {
    return this.http.get<AlunoDTO[]>(this.baseUrl);
  }

  buscarPorId(id: number): Observable<AlunoDTO> {
    return this.http.get<AlunoDTO>(`${this.baseUrl}/${id}`);
  }

  private buildPayload(dto: AlunoDTO): any {
    const cursoId = dto.curso?.identifier ?? null;
    return {
      ...dto,
      // manda o objeto mínimo aceito por Jackson/JPA
      curso: cursoId ? { identifier: Number(cursoId) } : null,
      // manda também o id plano para back-ends que esperam "cursoIdentifier" (compatibilidade)
      cursoIdentifier: cursoId ? Number(cursoId) : null
    };
  }

  salvar(dto: AlunoDTO): Observable<AlunoDTO> {
    const payload = this.buildPayload(dto);
    if (dto.identifier) {
      return this.http.put<AlunoDTO>(`${this.baseUrl}/${dto.identifier}`, payload);
    }
    return this.http.post<AlunoDTO>(this.baseUrl, payload);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
