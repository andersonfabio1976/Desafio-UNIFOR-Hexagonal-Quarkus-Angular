import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import {
  MatrizAlunoItem,
  MatrizAlunoResponse,
  MatrizProfessorResponse,
} from '../models/matriz';

@Injectable({ providedIn: 'root' })
export class MatrizService {
  // Compatível com apiBaseUrl OU apiUrl; com fallback
  private readonly baseRoot =
    ((environment as any).apiBaseUrl || (environment as any).apiUrl || 'http://localhost:8081/api')
      .toString()
      .replace(/\/+$/, '');

  constructor(private http: HttpClient) {}

  private withBase(path: string): string {
    const p = path.startsWith('/') ? path : `/${path}`;
    return `${this.baseRoot}${p}`;
  }

  /**
   * Aluno
   * - Se cursoId for informado, usa o endpoint LEGADO: GET /Matriz?cursoId=...
   *   (array de itens) e mapeia para { cursoId, itens }
   * - Caso contrário, tenta o endpoint NOVO: GET /matriz/aluno
   * - Em erro, devolve MOCK para não quebrar a tela
   */
  getMatrizAluno(cursoId?: number): Observable<MatrizAlunoResponse> {
    if (cursoId != null) {
      const urlLegacy = this.withBase(`/Matriz?cursoId=${cursoId}`);
      return this.http.get<MatrizAlunoItem[]>(urlLegacy).pipe(
        map((arr) => ({ cursoId, itens: arr })),
        catchError((err) => {
          // eslint-disable-next-line no-console
          console.warn('[MatrizService] fallback MOCK getMatrizAluno(LEGACY):', err?.message || err);
          return of(this.mockAluno());
        })
      );
    } else {
      const urlNew = this.withBase('/matriz/aluno');
      return this.http.get<MatrizAlunoResponse>(urlNew).pipe(
        catchError((err) => {
          // eslint-disable-next-line no-console
          console.warn('[MatrizService] fallback MOCK getMatrizAluno(NEW):', err?.message || err);
          return of(this.mockAluno());
        })
      );
    }
  }

  /**
   * Professor
   * - Se professorId for informado, chama GET /matriz/professor?professorId=...
   * - Caso contrário, chama GET /matriz/professor
   * - Em erro, devolve MOCK
   */
  getMatrizProfessor(professorId?: number): Observable<MatrizProfessorResponse> {
    const url =
      professorId != null
        ? this.withBase(`/matriz/professor?professorId=${professorId}`)
        : this.withBase('/matriz/professor');

    return this.http.get<MatrizProfessorResponse>(url).pipe(
      catchError((err) => {
        // eslint-disable-next-line no-console
        console.warn('[MatrizService] fallback MOCK getMatrizProfessor():', err?.message || err);
        return of(this.mockProfessor());
      })
    );
  }

  // ================= MOCKS =================
  private mockAluno(): MatrizAlunoResponse {
    return {
      cursoId: 1,
      itens: [
        { periodo: 1, disciplina: 'Algoritmos e Programação I', cargaHoraria: 80, responsavel: 'Prof. João Silva', status: 'APROVADA', nota: 8.5 },
        { periodo: 1, disciplina: 'Matemática Discreta',        cargaHoraria: 60, responsavel: 'Prof. Maria Santos', status: 'EM_ANDAMENTO' },
        { periodo: 2, disciplina: 'Estruturas de Dados',        cargaHoraria: 80, responsavel: 'Prof. João Silva',   status: 'PENDENTE' },
      ],
    };
  }

  private mockProfessor(): MatrizProfessorResponse {
    return {
      itens: [
        { periodo: 1, disciplina: 'Algoritmos e Programação I', cargaHoraria: 80, responsavel: 'Eu', turmas: 2, semestreLetivo: '2025.1' },
        { periodo: 2, disciplina: 'Estruturas de Dados',        cargaHoraria: 80, responsavel: 'Eu', turmas: 1, semestreLetivo: '2025.1' },
      ],
    };
  }
}
