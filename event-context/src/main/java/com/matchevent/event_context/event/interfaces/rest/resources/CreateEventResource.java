package com.matchevent.event_context.event.interfaces.rest.resources;

public record CreateEventResource(
        Long proposalId,
        String startDate,   // ISO 8601 ( 2025-07-15T18:00:00)
        String endDate,
        String description,
        String location) { }
