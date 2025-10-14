export interface AlunoDTO {
  identifier?: number;
  nome: string;
  email: string;
  dataNascimento: string; // yyyy-mm-dd
  matriculas?: any[]; // pode ser detalhado depois
  createdOn?: string; // ISO string
  updatedOn?: string; // ISO string
}
