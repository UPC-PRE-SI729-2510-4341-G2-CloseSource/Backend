package com.matchevent.event_context.event.interfaces.rest.resources;

public record UpdateEventStatusResource(Long eventId, String newStatus) { }
