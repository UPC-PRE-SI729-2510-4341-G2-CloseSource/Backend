package com.matchevent.event_context.event.domain.services;

import java.util.List;
import java.util.Optional;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.queries.GetEventByIdQuery;
import com.matchevent.event_context.event.domain.model.projections.EventView;

public interface EventQueryService {
    Optional<Event>      handle(GetEventByIdQuery query);      // write-side
    Optional<EventView>  handleAsView(GetEventByIdQuery query); // read-side
    List<Event> findAll();
}
