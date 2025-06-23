package com.matchevent.event_context.event.domain.services;

import java.util.Optional;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.commands.*;

public interface EventCommandService {
    Long handle(CreateEventCommand command);
    Optional<Event> handle(UpdateEventStatusCommand command);
    Long handle(AddGalleryItemCommand command);
}
