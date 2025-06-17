package com.matchevent.proposal_context.proposals.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public record ServiceId(Long value) implements Serializable {
    public ServiceId {
        if (value != null && value < 0)
            throw new IllegalArgumentException("ServiceId cannot be negative");
    }

    public ServiceId() {
        this(0L);
    }
}
