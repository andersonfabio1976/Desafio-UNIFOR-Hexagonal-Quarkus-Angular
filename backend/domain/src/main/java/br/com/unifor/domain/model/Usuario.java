package br.com.unifor.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends AuditableModel {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String keycloakIdentifier;
    private String password;
}
