export type StatusAluno = 'APROVADA' | 'EM_ANDAMENTO' | 'PENDENTE';

export interface MatrizItemBase {
  periodo: number;           // 1, 2, 3...
  disciplina: string;        // "Algoritmos e Programação I"
  cargaHoraria: number;      // 80
  responsavel: string;       // "Prof. João Silva" ou "Depto. X"
}

export interface MatrizAlunoItem extends MatrizItemBase {
  status?: StatusAluno;      // Aprovada / Em andamento / Pendente
  nota?: number | null;      // opcional
}

export interface MatrizAlunoResponse {
  cursoId?: string | number;
  itens: MatrizAlunoItem[];
}

export interface MatrizProfessorItem extends MatrizItemBase {
  turmas?: number;           // opcional (quantidade de turmas/ofertas)
  semestreLetivo?: string;   // opcional, ex.: "2025.1"
}

export interface MatrizProfessorResponse {
  itens: MatrizProfessorItem[];
}
