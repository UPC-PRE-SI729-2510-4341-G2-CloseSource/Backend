package com.matchevent.event_context.event.interfaces.rest.transform;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.interfaces.rest.resources.EventResource;

public class EventResourceFromEntityAssembler {
    public static EventResource toResourceFromEntity(Event e) {
        return new EventResource(
                e.getId(),
                e.getProposalId(),
                e.getStartDate(),
                e.getEndDate(),
                e.getStatus().name(),
                e.getDescription(),
                e.getLocation());
    }
}
