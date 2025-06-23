package com.matchevent.event_context.shared.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Pequeño “value type” reutilizable con marcas de auditoría.
 * Se incrusta con @Embedded en cualquier entidad/agregado.
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable           // <── ¡no @MappedSuperclass!
public class AuditableModel {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
