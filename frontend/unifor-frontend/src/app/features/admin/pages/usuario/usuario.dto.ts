export interface UsuarioDTO {
  identifier?: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  password?: string;
  keycloakIdentifier?: string;
}
