package com.matchevent.proposal_context.proposals.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public record ProducerId(Long value) implements Serializable {
    public ProducerId {
        if (value != null && value < 0)
            throw new IllegalArgumentException("ProducerId cannot be negative");
    }

    public ProducerId() {
        this(0L);
    }
}
