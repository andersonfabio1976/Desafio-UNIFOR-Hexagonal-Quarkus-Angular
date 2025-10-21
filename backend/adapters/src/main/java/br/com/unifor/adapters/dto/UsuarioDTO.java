package br.com.unifor.adapters.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsuarioDTO extends AuditableDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String keycloakIdentifier;
}
