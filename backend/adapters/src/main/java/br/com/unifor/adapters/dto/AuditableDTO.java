package br.com.unifor.adapters.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.Instant;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditableDTO {
    private Long identifier;
    private Instant createdOn;
    private Instant updatedOn;
}
