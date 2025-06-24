package com.matchevent.event_context.event.domain.model.projections;

import java.time.LocalDateTime;

public interface EventView {
    Long getId();
    Long getProposalId();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    String getStatus();      // enum → String
    String getDescription();
    String getLocation();
}
