package pe.edu.upc.matchevent.request.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public record EventDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    public EventDateRange {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    public EventDateRange() {
        this(null, null);
    }
}
