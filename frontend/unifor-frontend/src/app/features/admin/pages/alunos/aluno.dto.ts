import { UsuarioDTO } from '../usuario/usuario.dto';
import {CursosDTO} from "../cursos/cursos.dto"

export interface AlunoDTO {
  identifier?: number;
  nome: string;
  email: string;
  dataNascimento: string;
  usuario: UsuarioDTO;
  curso?: CursosDTO;
}
