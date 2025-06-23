package com.matchevent.event_context.event.interfaces.rest.resources;

import java.time.LocalDateTime;

public record EventResource(
        Long id,
        Long proposalId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String status,
        String description,
        String location) { }
