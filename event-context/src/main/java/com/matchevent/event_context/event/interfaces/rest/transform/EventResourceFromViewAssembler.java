package com.matchevent.event_context.event.interfaces.rest.transform;

import com.matchevent.event_context.event.domain.model.projections.EventView;
import com.matchevent.event_context.event.interfaces.rest.resources.EventResource;

public class EventResourceFromViewAssembler {

    public static EventResource toResource(EventView v) {
        return new EventResource(
                v.getId(),
                v.getProposalId(),
                v.getStartDate(),
                v.getEndDate(),
                v.getStatus(),
                v.getDescription(),
                v.getLocation()
        );
    }
}
