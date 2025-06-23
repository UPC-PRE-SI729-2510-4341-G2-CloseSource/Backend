package com.matchevent.event_context.event.domain.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Event with id " + id + " not found");
    }
}
