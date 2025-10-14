import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';

export interface AlunoDTO {
  identifier?: number;
  nome: string;
  email: string;
  dataNascimento: string;
  createdOn?: string;
  updatedOn?: string;
}

@Injectable({ providedIn: 'root' })
export class AlunosService {
  private base = `${environment.apiUrl}/alunos`;

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<AlunoDTO[]> {
    return this.http.get<AlunoDTO[]>(this.base);
  }

  salvar(dto: AlunoDTO): Observable<AlunoDTO> {
    if (dto.identifier) {
      return this.http.put<AlunoDTO>(`${this.base}/${dto.identifier}`, dto);
    }
    return this.http.post<AlunoDTO>(this.base, dto);
  }

  remover(id: number) {
    return this.http.delete(`${this.base}/${id}`);
  }
}
