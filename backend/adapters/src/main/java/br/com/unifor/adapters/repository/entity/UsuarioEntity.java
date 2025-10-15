package br.com.unifor.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UsuarioEntity extends AuditableEntity {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String keycloakIdentifier;
    private String password;
}
