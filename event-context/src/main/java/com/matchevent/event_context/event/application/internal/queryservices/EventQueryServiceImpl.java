package com.matchevent.event_context.event.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.projections.EventView;
import com.matchevent.event_context.event.domain.model.queries.GetEventByIdQuery;
import com.matchevent.event_context.event.domain.services.EventQueryService;
import com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories.EventRepository;
import com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories.EventViewRepository;

@Service
public class EventQueryServiceImpl implements EventQueryService {

    private final EventRepository     eventRepository;
    private final EventViewRepository eventViewRepository;

    public EventQueryServiceImpl(EventRepository eventRepository,
                                 EventViewRepository eventViewRepository) {
        this.eventRepository     = eventRepository;
        this.eventViewRepository = eventViewRepository;
    }

    /* -------- write-side (agregado completo) -------- */
    @Override
    public Optional<Event> handle(GetEventByIdQuery q) {
        return eventRepository.findById(q.eventId());
    }

    /* -------- read-side (proyección) -------- */
    @Override
    public Optional<EventView> handleAsView(GetEventByIdQuery q) {
        return eventViewRepository.findProjectedById(q.eventId());
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
