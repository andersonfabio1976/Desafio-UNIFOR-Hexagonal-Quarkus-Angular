export interface DisciplinaDTO {
  identifier?: number;
  nome: string;
  cargaHoraria?: number; // NOVO
  professor?: { identifier?: number; nome?: string };
  curso?: { identifier?: number; nome?: string };
  semestre?: { identifier?: number; numero?: number };
}
