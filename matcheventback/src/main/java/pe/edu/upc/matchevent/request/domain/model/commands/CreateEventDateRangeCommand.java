package pe.edu.upc.matchevent.request.domain.model.commands;

import java.time.LocalDateTime;

public record CreateEventDateRangeCommand(
        LocalDateTime startDate,
        LocalDateTime endDate
) {}
