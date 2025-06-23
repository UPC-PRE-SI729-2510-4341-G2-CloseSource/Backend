package com.matchevent.event_context.event.domain.model.commands;

public record AddGalleryItemCommand(Long eventId, String imageUrl, String caption) {}
