import { UsuarioDTO } from '../usuario/usuario.dto';

export interface AlunoDTO {
  identifier?: number;
  nome: string;
  email: string;
  dataNascimento: string;
  usuario: UsuarioDTO;
}
