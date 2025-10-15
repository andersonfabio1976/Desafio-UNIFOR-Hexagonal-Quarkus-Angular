// admin/pages/professores/professor.dto.ts
import { UsuarioDTO } from '../usuario/usuario.dto';

export interface ProfessorDTO {
  identifier?: number;
  nome: string;
  email: string;
  area: string;
  coordenador?: boolean;
  usuario: UsuarioDTO;
  createdOn?: string;
  updatedOn?: string;
}
