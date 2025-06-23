package com.matchevent.event_context.event.domain.model.commands;

import java.time.LocalDateTime;

public record CreateEventCommand(
        Long proposalId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String description,
        String location) {}
