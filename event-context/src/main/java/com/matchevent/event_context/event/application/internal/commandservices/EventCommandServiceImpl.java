package com.matchevent.event_context.event.application.internal.commandservices;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matchevent.event_context.event.domain.exceptions.EventNotFoundException;
import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.commands.*;
import com.matchevent.event_context.event.domain.model.entities.GalleryItem;
import com.matchevent.event_context.event.domain.services.EventCommandService;
import com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories.EventRepository;

@Service
@Transactional
public class EventCommandServiceImpl implements EventCommandService {

    private final EventRepository eventRepository;

    public EventCommandServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Long handle(CreateEventCommand cmd) {
        Event event = new Event(
                cmd.proposalId(),
                cmd.startDate(),
                cmd.endDate(),
                cmd.description(),
                cmd.location());
        return eventRepository.save(event).getId();
    }

    @Override
    public Optional<Event> handle(UpdateEventStatusCommand cmd) {
        return eventRepository.findById(cmd.eventId()).map(event -> {
            event.changeStatus(cmd.newStatus());
            return eventRepository.save(event);
        });
    }

    @Override
    public Long handle(AddGalleryItemCommand cmd) {
        Event event = eventRepository.findById(cmd.eventId())
                .orElseThrow(() -> new EventNotFoundException(cmd.eventId()));
        event.addGalleryItem(new GalleryItem(cmd.imageUrl(), cmd.caption()));
        return eventRepository.save(event).getId();
    }
}
