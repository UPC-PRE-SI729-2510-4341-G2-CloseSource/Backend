package com.matchevent.proposal_context.proposals.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public record ProposalId(Long value) implements Serializable {
    public ProposalId {
        if (value != null && value < 0)
            throw new IllegalArgumentException("ProposalId cannot be negative");
    }

    public ProposalId() {
        this(0L);
    }
}