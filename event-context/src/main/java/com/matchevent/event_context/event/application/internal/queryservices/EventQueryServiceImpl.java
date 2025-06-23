package com.matchevent.event_context.event.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.queries.GetEventByIdQuery;
import com.matchevent.event_context.event.domain.services.EventQueryService;
import com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories.EventRepository;

@Service
public class EventQueryServiceImpl implements EventQueryService {

    private final EventRepository eventRepository;

    public EventQueryServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> handle(GetEventByIdQuery query) {
        return eventRepository.findById(query.eventId());
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
