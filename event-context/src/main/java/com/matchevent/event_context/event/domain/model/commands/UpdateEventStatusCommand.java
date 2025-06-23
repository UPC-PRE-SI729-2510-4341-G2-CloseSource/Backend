package com.matchevent.event_context.event.domain.model.commands;

import com.matchevent.event_context.event.domain.model.valueobjects.EventStatus;

public record UpdateEventStatusCommand(Long eventId, EventStatus newStatus) {}
