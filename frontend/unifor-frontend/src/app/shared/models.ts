// Modelos mínimos para exibição nas tabelas do Coordenador
export interface Identifiable {
  identifier?: number;
}

// Disciplina
export interface DisciplinaDTO extends Identifiable {
  nome: string;
  professor?: { nome?: string };
  curso?: { nome?: string };
  semestre?: { numero?: number };
}

// Semestre
export interface SemestreDTO extends Identifiable {
  numero: number;
  curso?: { nome?: string };
}

