package br.com.unifor.domain.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AuditableModel {
    private Instant createdOn;
    private Instant updatedOn;
    private Long identifier;
}
