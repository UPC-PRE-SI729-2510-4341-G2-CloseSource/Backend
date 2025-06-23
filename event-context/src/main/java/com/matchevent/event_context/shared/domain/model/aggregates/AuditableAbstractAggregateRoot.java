package com.matchevent.event_context.shared.domain.model.aggregates;

import com.matchevent.event_context.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;          // ← import correcto
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

/**
 * Super-clase base para todos los Aggregate Roots:
 *  • id autoincremental
 *  • integración con Spring Domain Events
 *  • auditoría (created_at / updated_at)
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableAbstractAggregateRoot<T extends AuditableAbstractAggregateRoot<T>>
        extends AbstractAggregateRoot<T>
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Marcas de auditoría embebidas. */
    @Embedded
    private AuditableModel audit = new AuditableModel();
}
