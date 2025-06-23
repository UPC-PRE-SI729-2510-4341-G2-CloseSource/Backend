package com.matchevent.event_context.event.domain.services;

import java.util.List;
import java.util.Optional;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.queries.GetEventByIdQuery;

public interface EventQueryService {
    Optional<Event> handle(GetEventByIdQuery query);
    List<Event> findAll();
}
