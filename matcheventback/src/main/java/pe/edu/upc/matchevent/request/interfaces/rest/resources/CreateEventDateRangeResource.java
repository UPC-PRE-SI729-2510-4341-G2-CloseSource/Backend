package pe.edu.upc.matchevent.request.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateEventDateRangeResource(
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
